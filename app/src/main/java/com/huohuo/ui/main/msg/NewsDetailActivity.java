package com.huohuo.ui.main.msg;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dian.commonlib.base.BaseLoadActivity;
import com.dian.commonlib.utils.widget.MultipleStatusView;
import com.huohuo.R;
import com.huohuo.dao.table.NewsData;
import com.huohuo.mvp.model.bean.NewsList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsDetailActivity extends BaseLoadActivity {

    private static final String TAG = "NewsDetailActivity";

    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvNewsTitle)
    TextView tvNewsTitle;
    @BindView(R.id.tvNewsType)
    TextView tvNewsType;
    @BindView(R.id.tvNewsTime)
    TextView tvNewsTime;
    @BindView(R.id.tvNewsRead)
    TextView tvNewsRead;
    @BindView(R.id.tvNewsContent)
    TextView tvNewsContent;

    private static final String NEWS = "news";

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        NewsList.ListBean newsData = (NewsList.ListBean) intent.getExtras().getSerializable(NEWS);
        Log.i(TAG, "onNewIntent: " + newsData);
    }

    @Override
    public void initViewAndData() {
        super.initViewAndData();
        ivLeft.setVisibility(View.VISIBLE);
        ivLeft.setOnClickListener(view -> {
            finish();
        });
        tvNewsTitle.setText("习近平即将访问希腊 两国间还有段特殊情谊");
        tvNewsType.setText("义警新闻");
        tvNewsTime.setText("2019年11月09日 08:00");
        tvNewsRead.setText("29已读");
        tvNewsContent.setText("原标题：习近平即将访问希腊，两国间还有段特殊情谊\n" +
                "\n" +
                "　　[学习小组按]\n" +
                "\n" +
                "　　11月10日至15日，国家主席习近平将对希腊进行国事访问，并赴巴西出席金砖国家领导人第十一次会晤。\n" +
                "\n" +
                "　　这也是党的十九届四中全会后，习近平首次出访。\n" +
                "\n" +
                "　　2014年，习近平在出席金砖国家领导人第六次会晤途中，就在希腊进行技术经停，会见希腊领导人，并就两国关系发展交换意见。\n" +
                "\n" +
                "　　两千多年前，古代海上丝绸之路将中国和希腊这两个东西文明的起源地紧密联系在一起。跨越千年后，在两国领导人的共同努力下，21世纪海上丝绸之路重新为中国和希腊的友谊注入了新动力。" +
                "文明古国的对话\n" +
                "\n" +
                "　　希腊文明诞生于巴尔干半岛和爱琴海域。海洋造就了希腊人探索性的思想，尤其是对自然的观察并进行理性的思考。这就促成了哲学的产生，并使希腊成为后来欧洲文明发展的摇篮。历经千年，苏格拉底、柏拉图、亚里士多德等希腊哲学家名字，仍是璀璨西方历史星空的明星。\n" +
                "\n" +
                "　　希腊在内的西方文明，与中华文明代表的东方文明，向来是在交流互鉴中向前进步。近代以来，欧洲从中华文化中汲取智慧养分，助力了启蒙运动的进程；中华文明也以海纳百川的胸怀，对西方工业、法治、管理等许多领域的文明成果广泛吸收，在兼收并蓄中历久弥新。\n" +
                "\n" +
                "　　习近平曾说过，“丰富多彩的人类文明都有自己存在的价值。要理性处理本国文明与其他文明的差异，认识到每一个国家和民族的文明都是独特的。”也正是基于东西文明互鉴的共识，中国和希腊这两个文明古国走到了一起。\n" +
                "\n" +
                "　　2017年，在中希两国的共同倡议下发起“文明古国论坛”，探讨人类原创型文明给当代社会带来的有益启示，推动古老文化传统焕发出新的时代活力。\n" +
                "\n" +
                "　　今年5月，希腊总统帕夫洛普洛斯来华进行国事访问并出席亚洲文明对话大会时，对所谓的“文明冲突论”进行了驳斥，认为“真正的文明之间不应也不会发生冲突对抗。不同文明之间存在差异，应相互尊重，通过对话交流，相互借鉴，取长补短，这才是世界持久和平和人类和谐共处之道。”\n" +
                "\n" +
                "　　中希两个文明古国的交流，让文明对话超越了所谓的文明冲突，也让我们对两国的再次对话充满了期待。\n" +
                "\n" +
                "　　海上丝路的情缘\n" +
                "\n" +
                "　　古希腊是近海国家，海洋贸易文明极为发达。希腊与中国也因海洋而结缘。\n" +
                "\n" +
                "　　两千多年前，海上丝绸之路从中国东南沿海，经过中南半岛和南海诸国，穿过印度洋，进入红海，抵达东非和欧洲，成为中国与外国贸易往来和文化交流的海上大通道。当时，中国一直是希腊人在内的西方人向往的地方。\n" +
                "\n" +
                "　　2005年，希腊经济陷入困局，很多企业的工人都开始举行大规模罢工。2008年，中远海运集团在比雷埃夫斯港集装箱2号、3号码头私有化招标中中标，获得了其35年特许经营权，于2010年正式接管该码头经营。\n" +
                "\n" +
                "　　希腊经济和工业研究基金会报告显示，中远海运与比雷埃夫斯港港务局签订的协议，可为希腊经济贡献15亿欧元，并创造12.5万个直接和间接就业岗位。依托比雷埃夫斯港，铁运和海运实现了有机融合。中欧陆海快运从这里出发，可为远东至中东欧腹地提供更为便捷、低成本的通道。\n" +
                "\n" +
                "　　2018年8月27日，希腊成为与中方签订建“一带一路”合作谅解备忘录的首个欧洲发达国家。\n" +
                "\n" +
                "　　中希两国通过比雷埃夫斯港的合作，通过东西文化的对话沟通，为这条历史上曾经是东西方文明交流的大动脉的海上丝绸之路注入了新的活力。\n" +
                "\n" +
                "　　雪中送炭的友谊 \n" +
                "\n" +
                "　　对于希腊，很多人仍有中国帮助购买希腊债券的回忆。的确，两国之间有着互相雪中送炭的友谊往事。\n" +
                "\n" +
                "　　2009年，欧洲爆发欧债危机，希腊深陷泥潭，经济总量大幅缩水。在欧盟提出苛刻援助条件的情况下，希腊亟须国际资金投入。\n" +
                "\n" +
                "　　据不完全统计，希腊总投资金额的40%来自中国。正如希腊前财政部长帕帕康斯坦季努所说的，“没人愿意投资希腊的时候，是中国伸出了援手。”\n" +
                "\n" +
                "　　历史也证明了，希腊是中国最可信赖的朋友之一。\n" +
                "\n" +
                "　　1972年两国建交前，中国受到了一些西方的国家“禁运封锁”，国家发展亟须的物资短缺。一些希腊船东就冲破禁运，帮助新中国运输物资，为新中国的发展贡献了自己的力量。\n" +
                "\n" +
                "　　2008年中国汶川遭受地震后，希腊人民坚定和中国人民站在一起，其中，希腊船东代表积极为灾区捐款21.5万美元。\n" +
                "\n" +
                "　　2011年的利比亚危机时，中国政府决定将利比亚侨民紧急通过希腊撤回。希腊也在3小时内做出反应，决定助华撤侨。当时，被紧急营救到希腊的中国侨民，几乎没有一个人是证件齐全的。但希腊政府顶着压力，使得“无证”的1.3万名中国侨民顺利借道回国。希腊甚至派军舰主动帮助中国侨民撤离利比亚。海关人员也特事特办，无需中国公民在场，便给他们办理好入境希腊的手续。\n" +
                "\n" +
                "　　真正的友谊，是在朋友困难时互相伸出援手。当我们看到中希两国雪中送炭友谊往事时候，就更能理解友谊的含义和构建人类命运共同体的意义。\n" +
                "\n" +
                "　　“修昔底德陷阱”的跨越\n" +
                "\n" +
                "　　经过改革开放40多年的砥砺奋进，中国飞速发展，逐渐走近世界舞台中心。与此同时，西方一些政客对中国崛起的焦虑感上升。所谓“修昔底德陷阱”在西方不时受到热炒。一些学者认定，中美两个大国难以跨越这一“陷阱”，最终必将走向冲突与对抗。\n" +
                "\n" +
                "　　“修昔底德陷阱”故事的发生地，就是这次习近平访问的希腊。2400多年前，古希腊爆发雅典和斯巴达之间的伯罗奔尼撒战争。这场战争几乎摧毁了雅典的盛世，导致战后希腊奴隶制城邦的危机，使整个希腊开始由盛转衰。\n" +
                "\n" +
                "　　实际上，“修昔底德陷阱”并非古希腊历史学家修昔底德提出。他只是在《伯罗奔尼撒战争史》中写道，雅典的日益壮大以及斯巴达对雅典的担心，使得二者爆发战争。\n" +
                "\n" +
                "　　对于“修昔底德陷阱”，习近平有过多次论述。2014年，习近平在接受美国《赫芬顿邮报》子报《世界邮报》专访时表示：“我们都应该努力避免陷入‘修昔底德陷阱’，强国只能追求霸权的主张不适用于中国，中国没有实施这种行动的基因”。2015年，习近平在美国华盛顿州当地政府和美国友好团体联合举行的欢迎宴会上发表演讲时又指出：“世界上本无‘修昔底德陷阱’，但大国之间一再发生战略误判，就可能自己给自己造成‘修昔底德陷阱’”。\n" +
                "\n" +
                "　　在这次进博会开幕式上，习近平说，国家间开放合作，应该“坚持‘拉手’而不是‘松手’，坚持‘拆墙’而不是‘建墙’”。他也在多个场合提到，大国间要管控分歧，防止战略误判，唯有如此，才能推动世界和平与繁荣。中国与希腊之间的交往，便是国与国之间文明交流互鉴，发展互利共赢的生动写照。\n" +
                "\n" +
                "　　作者/万喆\n" +
                "\n" +
                "　　中国黄金集团首席经济学家、丝路产业与金融国际联盟首席经济学家\n" +
                "\n" +
                "责任编辑：吴金明");

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_news_detail;
    }


    @Override
    public void retry() {

    }

    @Override
    public MultipleStatusView getMultipleStatusView() {
        return null;
    }

}
