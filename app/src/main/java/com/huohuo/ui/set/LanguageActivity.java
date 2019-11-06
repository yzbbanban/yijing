package com.huohuo.ui.set;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.dian.commonlib.base.BaseActivity;
import com.dian.commonlib.lang.MultiLanguageUtil;
import com.dian.commonlib.utils.widget.BaseCheckAdapter;
import com.huohuo.R;
import com.huohuo.app.HuoHuoConstants;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by kennysun on 2019/8/28.
 */

public class LanguageActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    int selectedLanguage;//上一次选择的语言
    @BindView(R.id.tvChinese)
    TextView tvChinese;
    @BindView(R.id.ivChinese)
    ImageView ivChinese;
    @BindView(R.id.clChinese)
    ConstraintLayout clChinese;
    @BindView(R.id.tvKorea)
    TextView tvKorea;
    @BindView(R.id.ivKorea)
    ImageView ivKorea;
    @BindView(R.id.clKorea)
    ConstraintLayout clKorea;
    @BindView(R.id.tvEnglish)
    TextView tvEnglish;
    @BindView(R.id.ivEnglish)
    ImageView ivEnglish;
    @BindView(R.id.clEnglish)
    ConstraintLayout clEnglish;
    private String className;

    @Override
    public int getLayoutId() {
        return R.layout.activity_language;
    }

    @Override
    public void initViewAndData() {
        setToolbarConfig(toolbar, R.string.change_language);
        className = getIntent().getStringExtra(HuoHuoConstants.CLASSNAME);
        selectedLanguage = MultiLanguageUtil.getInstance().getLanguageType();
        showCheck();
    }

    private void showCheck() {
        switch (selectedLanguage) {
            case MultiLanguageUtil.LANGUAGE_CHINESE_SIMPLIFIED:
                ivChinese.setVisibility(View.VISIBLE);
                ivEnglish.setVisibility(View.GONE);
                ivKorea.setVisibility(View.GONE);
                break;
            case MultiLanguageUtil.LANGUAGE_EN:
                ivChinese.setVisibility(View.GONE);
                ivKorea.setVisibility(View.GONE);
                ivEnglish.setVisibility(View.VISIBLE);
                break;
            case MultiLanguageUtil.LANGUAGE_KO:
                ivChinese.setVisibility(View.GONE);
                ivEnglish.setVisibility(View.GONE);
                ivKorea.setVisibility(View.VISIBLE);
                break;
        }
    }

    private void setLanguage() {
        MultiLanguageUtil.getInstance().updateLanguage(selectedLanguage);
        // 设置完语言后返回的界面可能是MainActivitty,LoginActivity,RegistActivity...
        if (TextUtils.isEmpty(className)) {
            className = "com.huohuo.ui.main.MainActivity";
        }
        try {
            Intent intent = new Intent(LanguageActivity.this, Class.forName(className));
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        } catch (ClassNotFoundException e) {
        }
    }


    @OnClick({R.id.clChinese, R.id.clKorea, R.id.clEnglish})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.clChinese:
                selectedLanguage = MultiLanguageUtil.LANGUAGE_CHINESE_SIMPLIFIED;
                break;
            case R.id.clKorea:
                selectedLanguage = MultiLanguageUtil.LANGUAGE_KO;
                break;
            case R.id.clEnglish:
                selectedLanguage = MultiLanguageUtil.LANGUAGE_CHINESE_SIMPLIFIED;
                break;
        }
        showCheck();
        setLanguage();
    }
}
