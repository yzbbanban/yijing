package com.dian.commonlib.utils.widget.paydialog;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.RelativeLayout;

import com.dian.commonlib.R;

import java.util.ArrayList;

/**
 * Created by kennysun on 2019/8/29.
 */

public class PayKeyBoard extends RelativeLayout implements View.OnClickListener {

    Context context;

    private GridView gridView;

    private RelativeLayout layoutBack;

    private ArrayList<String> valueList;

    private String currentValue = "";

    public PayKeyBoard(Context context) {
        this(context, null);
    }

    public PayKeyBoard(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public PayKeyBoard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        View view = View.inflate(context, R.layout.layout_pay_keyboard, null);

        valueList = new ArrayList<>();

        layoutBack = view.findViewById(R.id.layoutBack);
        layoutBack.setOnClickListener(this);

        gridView = view.findViewById(R.id.gvKeyBoard);

        setView();

        addView(view);
    }

    private void setView() {
//        初始化按钮上显示的数字
        for (int i = 1; i < 10; i++) {
            this.valueList.add(String.valueOf(i));
        }
        this.valueList.add(" ");
        this.valueList.add("0");
        this.valueList.add("del");

        PayKeyBoardAdapter adapter = new PayKeyBoardAdapter(context, this.valueList);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String value = PayKeyBoard.this.valueList.get(position);
                switch (value) {
                    case " ":
//                        if(PayKeyBoard.this.currentValue.contains(".")){
//                            return;
//                        } else {
//                            PayKeyBoard.this.currentValue += ".";
//                        }
                        return;
                    case "del":
                        if(PayKeyBoard.this.currentValue.length() > 0){
                            PayKeyBoard.this.currentValue = PayKeyBoard.this.currentValue.substring(0,PayKeyBoard.this.currentValue.length()-1);
                        }
                        break;
                    default:
                        PayKeyBoard.this.currentValue += value;
                        break;
                }
                if(PayKeyBoard.this.onChangedListener != null){
                    PayKeyBoard.this.onChangedListener.onChanged(PayKeyBoard.this.currentValue);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.layoutBack){
            setVisibility(GONE);
        }
    }

    public void clear(){
        PayKeyBoard.this.currentValue = "";
    }

    public void show(){
        setVisibility(VISIBLE);
    }

    private OnChangedListener onChangedListener;

    public void setOnChangedListener(OnChangedListener onChangedListener) {
        this.onChangedListener = onChangedListener;
    }

    public void hide() {
        setVisibility(GONE);
    }

    public interface OnChangedListener {
        void onChanged(String value);
    }

}
