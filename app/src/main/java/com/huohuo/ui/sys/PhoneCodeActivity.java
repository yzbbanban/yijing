package com.huohuo.ui.sys;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;

import com.bigkoo.quicksidebar.QuickSideBarTipsView;
import com.bigkoo.quicksidebar.QuickSideBarView;
import com.bigkoo.quicksidebar.listener.OnQuickSideBarTouchListener;
import com.dian.commonlib.base.BaseLoadActivity;
import com.dian.commonlib.utils.widget.MultipleStatusView;
import com.huohuo.R;
import com.huohuo.app.HuoHuoConstants;
import com.huohuo.ui.adapter.PhoneCodeWithHeaderAdapter;
import com.huohuo.mvp.contract.sys.PhoneCodeContract;
import com.huohuo.mvp.model.bean.CountryCodeBean;
import com.huohuo.mvp.presenter.sys.PhoneCodePresenter;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersDecoration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;

/**
 * Created by kennysun on 2019/8/28.
 */

public class PhoneCodeActivity extends BaseLoadActivity implements PhoneCodeWithHeaderAdapter.OnItemClickListener, OnQuickSideBarTouchListener, PhoneCodeContract.View {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.quickSideBarTipsView)
    QuickSideBarTipsView quickSideBarTipsView;
    @BindView(R.id.quickSideBarView)
    QuickSideBarView quickSideBarView;
    @BindView(R.id.viewParent)
    RelativeLayout viewParent;
    @BindView(R.id.multipleStatusView)
    MultipleStatusView multipleStatusView;
    private PhoneCodeWithHeaderAdapter phoneCodeAdapter;
    private PhoneCodePresenter phoneCodePresenter;
    HashMap<String, Integer> letters = new HashMap<>();
    @Override
    public int getLayoutId() {
        return R.layout.activity_phone_code;
    }

    @Override
    public void retry() {

    }

    @Override
    public MultipleStatusView getMultipleStatusView() {
        return multipleStatusView;
    }

    @Override
    public void initViewAndData() {
        super.initViewAndData();
        setToolbarConfig(toolbar,R.string.phone_code);

        phoneCodePresenter = new PhoneCodePresenter();
        phoneCodePresenter.attachView(this,this);
        //设置监听
        quickSideBarView.setOnQuickSideBarTouchListener(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        phoneCodeAdapter = new PhoneCodeWithHeaderAdapter();
        phoneCodeAdapter.setOnItemClickListener(this);

        phoneCodePresenter.getCountryCode();
    }

    @Override
    public void onLetterChanged(String letter, int position, float y) {
        quickSideBarTipsView.setText(letter, position, y);
        //有此key则获取位置并滚动到该位置
        if (letters.containsKey(letter)) {
            recyclerView.scrollToPosition(letters.get(letter));
        }
    }

    @Override
    public void onLetterTouching(boolean touching) {
        //可以自己加入动画效果渐显渐隐
        quickSideBarTipsView.setVisibility(touching ? View.VISIBLE : View.INVISIBLE);
    }

    @Override
    public void onItemClick(CountryCodeBean country) {
        Intent intent = new Intent();
        intent.putExtra(HuoHuoConstants.COUNTRY_CODE, country.getCountryCode());
        setResult(RESULT_OK, intent);
        finish();
    }

    private ArrayList<String> letterList;

    @Override
    public void getCountryCode(ArrayList<String> customLetters, HashMap<String, Integer> letters, List<CountryCodeBean> countryCodes) {
       this.letterList  =customLetters;
       this.letters = letters;
        quickSideBarView.setLetters(letterList);
        phoneCodeAdapter.addAll(countryCodes);
        recyclerView.setAdapter(phoneCodeAdapter);

        final StickyRecyclerHeadersDecoration headersDecor = new StickyRecyclerHeadersDecoration(phoneCodeAdapter);
        recyclerView.addItemDecoration(headersDecor);
    }

}
