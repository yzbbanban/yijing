package com.yjb.ui.main.finance;

import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.dian.commonlib.base.BaseLoadActivity;
import com.dian.commonlib.utils.widget.MultipleStatusView;
import com.yjb.R;

import butterknife.BindView;

public class TeamIntroActivity extends BaseLoadActivity {


    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.BBCC_webview)
    WebView BBCCWebview;

    @Override
    public int getLayoutId() {
        return R.layout.activity_team_intro;
    }


    @Override
    public void retry() {

    }

    @Override
    public MultipleStatusView getMultipleStatusView() {
        return null;
    }

    @Override
    public void initViewAndData() {
        super.initViewAndData();
        tvTitle.setText("队伍介绍");
        ivLeft.setVisibility(View.VISIBLE);
        ivLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        String content = getIntent().getStringExtra("CONTENT");

        BBCCWebview.setWebViewClient(new WebViewClient());

        /**
         * 存储的html格式
         */
        String NOTICE_FORMAT = "" +
                "<!DOCTYPE html>" +
                "<html>" +
                "<head>" +
                "<meta charset=\"UTF-8\"> " +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">" +
                "</head>" +
                "<body>" +
                "%s" +
                "</body>" +
                "</html> ";

        String ht = String.format(NOTICE_FORMAT, content);
        /**
         * 将文本HTML显示在webview中
         */
        BBCCWebview.loadDataWithBaseURL(null, ht, "text/html", "UTF-8", null);

    }
}
