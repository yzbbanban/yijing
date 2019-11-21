package com.huohuo.ui.main.finance;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.codbking.widget.DatePickDialog;
import com.codbking.widget.OnSureLisener;
import com.codbking.widget.bean.DateType;
import com.dian.commonlib.base.BaseLoadActivity;
import com.dian.commonlib.utils.AppUtil;
import com.dian.commonlib.utils.ToastUtil;
import com.dian.commonlib.utils.widget.MultipleStatusView;
import com.huohuo.R;
import com.huohuo.mvp.contract.home.CommonUploadContract;
import com.huohuo.mvp.contract.home.YjApplyContract;
import com.huohuo.mvp.presenter.home.CommonUploadPresenter;
import com.huohuo.mvp.presenter.home.YjApplyPresenter;
import com.lljjcoder.citypickerview.widget.CityPicker;
import com.wildma.pictureselector.PictureSelector;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class YiApplyActivity extends BaseLoadActivity implements YjApplyContract.View, CommonUploadContract.View {


    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.tvName)
    EditText tvName;
    @BindView(R.id.tvRemark)
    EditText tvRemark;
    @BindView(R.id.tvDate)
    TextView tvDate;
    @BindView(R.id.tvGender)
    TextView tvGender;
    @BindView(R.id.tvZZ)
    EditText tvZZ;
    @BindView(R.id.tvIdentity)
    EditText tvIdentity;
    @BindView(R.id.tvWork)
    EditText tvWork;
    @BindView(R.id.ivPhoto)
    ImageView ivPhoto;
    @BindView(R.id.tvHome)
    TextView tvHome;
    private CityPicker cityPicker;

    private YjApplyPresenter yjApplyPresenter;

    private CommonUploadPresenter commonUploadPresenter;

    private String imageUrl = "";

    @Override
    public void retry() {

    }

    @Override
    public MultipleStatusView getMultipleStatusView() {
        return null;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_yi_apply;
    }

    @Override
    public void initViewAndData() {
        super.initViewAndData();
        tvTitle.setText("义警申请");
        ivLeft.setVisibility(View.VISIBLE);
        ivLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        yjApplyPresenter = new YjApplyPresenter();
        commonUploadPresenter = new CommonUploadPresenter();
        yjApplyPresenter.attachView(this, this);
        commonUploadPresenter.attachView(this, this);

        tvRight.setVisibility(View.VISIBLE);
        tvRight.setText("提交");
        tvRight.setTextColor(getResources().getColor(R.color.colorAccent));
        tvRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String vision = tvRemark.getText().toString();
                String photoimage = imageUrl;
                if (imageUrl == null) {
                    ToastUtil.show(YiApplyActivity.this, "请先上传图片");
                    return;
                }
                String homeaddress = tvHome.getText().toString();
                String politically = tvZZ.getText().toString();
                String identifier = tvIdentity.getText().toString();
                String job = tvWork.getText().toString();
                String nickname = tvName.getText().toString();
                String gender = tvGender.getText().toString();
                String birthday = tvDate.getText().toString();
                yjApplyPresenter.getList(AppUtil.getToken(), AppUtil.getUser(), vision, photoimage,
                        homeaddress, politically, identifier, job, nickname, gender, birthday);

            }
        });

        ivPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PictureSelector
                        .create(YiApplyActivity.this, PictureSelector.SELECT_REQUEST_CODE)
                        .selectPicture(false, 200, 200, 1, 1);
            }
        });


        tvDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickDialog dialog = new DatePickDialog(YiApplyActivity.this);
                //设置上下年分限制
                dialog.setYearLimt(5);
                //设置标题
                dialog.setTitle("选择时间");
                //设置类型
                dialog.setType(DateType.TYPE_YMD);
                //设置消息体的显示格式，日期格式
                dialog.setMessageFormat("yyyy-MM-dd");
                //设置选择回调
                dialog.setOnChangeLisener(null);
                //设置点击确定按钮回调
                dialog.setOnSureLisener(new OnSureLisener() {
                    @Override
                    public void onSure(Date date) {
                        tvDate.setText(new SimpleDateFormat("yyyy-MM-dd").format(date));
                    }
                });
                dialog.show();
            }
        });

        tvGender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSexChooseDialog();
            }
        });

        tvHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initCityPicker();
                cityPicker.show();
            }
        });
    }

    private String[] sexArry = new String[]{"女", "男"};// 性别选择


    private void showSexChooseDialog() {
        AlertDialog.Builder builder3 = new AlertDialog.Builder(this);// 自定义对话框
        builder3.setSingleChoiceItems(sexArry, 0, new DialogInterface.OnClickListener() {// 2默认的选中

            @Override
            public void onClick(DialogInterface dialog, int which) {// which是被选中的位置
                // showToast(which+"");
                tvGender.setText(sexArry[which]);
                dialog.dismiss();// 随便点击一个item消失对话框，不用点击确认取消
            }
        });
        builder3.show();// 让弹出框显示
    }

    public void initCityPicker() {

        //滚轮文字的大小
        //滚轮文字的颜色
        //省份滚轮是否循环显示
        //城市滚轮是否循环显示
        //地区（县）滚轮是否循环显示
        //滚轮显示的item个数
        //滚轮item间距
        cityPicker = new CityPicker.Builder(YiApplyActivity.this)
                .textSize(20)//滚轮文字的大小
                .title("地址选择")
                .backgroundPop(0xa0000000)
                .titleBackgroundColor("#0CB6CA")
                .titleTextColor("#000000")
                .backgroundPop(0xa0000000)
                .confirTextColor("#000000")
                .cancelTextColor("#000000")
                .province("xx省")
                .city("xx市")
                .district("xx区")
                .textColor(Color.parseColor("#000000"))//滚轮文字的颜色
                .provinceCyclic(true)//省份滚轮是否循环显示
                .cityCyclic(false)//城市滚轮是否循环显示
                .districtCyclic(false)//地区（县）滚轮是否循环显示
                .visibleItemsCount(7)//滚轮显示的item个数
                .itemPadding(10)//滚轮item间距
                .onlyShowProvinceAndCity(false)
                .build();

        //监听方法，获取选择结果
        cityPicker.setOnCityItemClickListener(new CityPicker.OnCityItemClickListener() {
            @Override
            public void onSelected(String... citySelected) {
                //省份
                String province = citySelected[0];
                //城市
                String city = citySelected[1];
                //区县（如果设定了两级联动，那么该项返回空）
                String district = citySelected[2];
                //邮编
                String code = citySelected[3];

                //地区text赋值
                tvHome.setText(province + city + district);

            }

            @Override
            public void onCancel() {


            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /*结果回调*/
        if (requestCode == PictureSelector.SELECT_REQUEST_CODE) {
            if (data != null) {
                String picturePath = data.getStringExtra(PictureSelector.PICTURE_PATH);
                ToastUtil.show(YiApplyActivity.this, picturePath);
                RequestOptions requestOptions = RequestOptions
                        .centerInsideTransform()
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .skipMemoryCache(true);
                Glide.with(this).load(picturePath).apply(requestOptions).into(ivPhoto);
                //上传图片
                File file = new File(picturePath);
                RequestBody requestFile =
                        RequestBody.create(MediaType.parse("application/otcet-stream"), file);

                MultipartBody.Part body =
                        MultipartBody.Part.createFormData("aFile", file.getName(), requestFile);

                String descriptionString = "This is a description";
                RequestBody description =
                        RequestBody.create(
                                MediaType.parse("multipart/form-data"), descriptionString);

                commonUploadPresenter.getList(AppUtil.getToken(), description);

            }
        }
    }

    @Override
    public void getApplySuccess(String o) {
        ToastUtil.show(YiApplyActivity.this, "提交了");
        imageUrl = null;
    }

    @Override
    public void getUpload(String o) {
        imageUrl = o;
        ToastUtil.show(YiApplyActivity.this, "上传图片成功");
    }
}
