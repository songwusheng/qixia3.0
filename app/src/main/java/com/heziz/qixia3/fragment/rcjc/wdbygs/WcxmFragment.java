package com.heziz.qixia3.fragment.rcjc.wdbygs;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.heziz.qixia3.R;
import com.heziz.qixia3.adaper.wdbygs.WCListAdapter;
import com.heziz.qixia3.adaper.wdbygs.YCListAdapter;
import com.heziz.qixia3.app.MyApplication;
import com.heziz.qixia3.base.BaseFragment;
import com.heziz.qixia3.bean.StreetBean;
import com.heziz.qixia3.bean.UserInfor;
import com.heziz.qixia3.bean.wdbygs.WDBYGSBean;
import com.heziz.qixia3.network.API;
import com.heziz.qixia3.network.JsonCallBack1;
import com.heziz.qixia3.network.OkGoClient;
import com.heziz.qixia3.network.RequestBean1;
import com.heziz.qixia3.network.SRequstBean;
import com.heziz.qixia3.view.SpinnerPopuwindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 *
 */
@SuppressLint("ValidFragment")
public class WcxmFragment extends BaseFragment implements View.OnClickListener {

    @BindView(R.id.recycleView)
    RecyclerView recycleView;
    @BindView(R.id.tvJd)
    TextView tvJd;
    @BindView(R.id.tvZT)
    TextView tvZT;
    @BindView(R.id.llJd)
    LinearLayout llJd;
    @BindView(R.id.ll)
    LinearLayout ll;
    @BindView(R.id.etName)
    EditText etName;
    @BindView(R.id.btnSearch)
    Button btnSearch;
    private String type_jd;
    private SpinnerPopuwindow jdSpinnerPopuwindow;
    private List<WDBYGSBean> list=new ArrayList<>();
    private WCListAdapter adapter;
    private UserInfor userInfor;
    Map<String,String> params=new HashMap<>();
    Map<String,String> params2=new HashMap<>();
    private int pageNow=1;
    /** 街道数据*/
    private List<StreetBean> streetBeanList=new ArrayList<>();
    private List<String> jdData;

    private String type;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @SuppressLint("ValidFragment")
    public WcxmFragment(String type){
        this.type=type;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wcxm, container, false);
        ButterKnife.bind(this, view);
        initViews();
        initDatas();
        initListeners();
        return view;
    }

    private void initViews() {
        tvZT.setVisibility(View.GONE);
        userInfor= MyApplication.getInstance().getUserInfor();
        adapter=new WCListAdapter(getActivity(),list);
        LinearLayoutManager manager=new LinearLayoutManager(getActivity().getApplicationContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recycleView.setLayoutManager(manager);
        recycleView.setAdapter(adapter);
        adapter.bindToRecyclerView(recycleView);
    }

    private void initDatas() {
        showProgressDialog();
        String url = API.STREET_LIST;
        Map<String,String> params1=new HashMap<>();
        JsonCallBack1<SRequstBean<List<StreetBean>>> jsonCallBack = new JsonCallBack1<SRequstBean<List<StreetBean>>>() {
            @Override
            public void onSuccess(com.lzy.okgo.model.Response<SRequstBean<List<StreetBean>>> response) {
                List<StreetBean> list=response.body().getData();
                if(userInfor.getPosition().equals("2")){
                    for(int i=0;i<list.size();i++){
                        if(String.valueOf(list.get(i).getId()).equals(userInfor.getManagerId())){
                            streetBeanList.add(list.get(i));
                            if(userInfor.getPosition().equals("2")){
                                for(int j=0;j<list.size();j++){
                                    if(userInfor.getManagerId().equals(list.get(j).getId()+"")){
                                        tvJd.setText(list.get(j).getName());
                                    }
                                }
                            }
                        }
                    }
                }else{
                    streetBeanList.addAll(response.body().getData());
                }
                StreetData();
            }

            @Override
            public void onError(com.lzy.okgo.model.Response<SRequstBean<List<StreetBean>>> response) {
                super.onError(response);
                dissmissProgressDialog();
            }

        };
        OkGoClient.getInstance()
                .getJsonData1(url, params1, jsonCallBack);


        params.put("managerRoleIds","");
        params.put("name","");
        if("1".equals(userInfor.getPosition())){
            params.put("station",userInfor.getStation()+"");
        }else if("2".equals(userInfor.getPosition())){
            params.put("managerRoleIds","["+userInfor.getManagerId()+"]");
        }else if("3".equals(userInfor.getPosition())){
            params.put("createName",userInfor.getAccount()+"");
        }

        getData();
    }

    private void getData(){

        String url = API.WDBYGS_WC_LIST+"?access_token="+userInfor.getUuid()+"&type="+type+"&pageNow="+pageNow+"&pageSize=20";
        JsonCallBack1<SRequstBean<RequestBean1<List<WDBYGSBean>>>> jsonCallBack = new JsonCallBack1<SRequstBean<RequestBean1<List<WDBYGSBean>>>>() {
            @Override
            public void onSuccess(com.lzy.okgo.model.Response<SRequstBean<RequestBean1<List<WDBYGSBean>>>> response) {
                dissmissProgressDialog();
                if (response.body().getData()!=null){
//                            carDDetailsBeanList.addAll(response.body().getData().getList());
                    if(response.body().getData().getVos().size()!=0){
                        list.addAll(response.body().getData().getVos());
                        adapter.loadMoreComplete();
                    }else{
                        adapter.loadMoreEnd();
                    }
                }else{
                    adapter.loadMoreFail();
                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(com.lzy.okgo.model.Response<SRequstBean<RequestBean1<List<WDBYGSBean>>>> response) {
                super.onError(response);
                dissmissProgressDialog();
            }

        };
        OkGoClient.getInstance()
                .postJsonData(url, params,jsonCallBack);
    }

    private void initListeners() {

        if(userInfor.getPosition().equals("1")){
            llJd.setOnClickListener(this);
        }
        btnSearch.setOnClickListener(this);

        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                pageNow++;
                getData();
            }
        });
    }


    /**
     * 街道数据
     */
    private void StreetData() {
        jdData = new ArrayList<>();
        jdData.add("全部");
        for(int i=0;i<streetBeanList.size();i++){
            jdData.add(streetBeanList.get(i).getName());
        }

    }
    private AdapterView.OnItemClickListener jditemsOnClick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String value = jdData.get(jdSpinnerPopuwindow.getText());
            tvJd.setText(value);
            jdSpinnerPopuwindow.dismissPopupWindow();
            if(userInfor.getPosition().equals("2")){

            }else{
                if(position==0){
                    params.put("managerRoleIds","");
                }else{
                    params.put("managerRoleIds","["+streetBeanList.get(position-1).getId()+"]");
                }
            }
            refresh();
        }
    };
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.llJd:
                type_jd = tvJd.getText().toString();
                jdSpinnerPopuwindow = new SpinnerPopuwindow(getActivity(),type_jd,jdData,jditemsOnClick);
                jdSpinnerPopuwindow.showPopupWindow(ll);
                jdSpinnerPopuwindow.setTitleText("街道");//给下拉列表设置标题
                break;
            case R.id.btnSearch:
                go();
                String s=etName.getText().toString();
                params.put("name",s);
                refresh();
                break;
        }

    }
    private void refresh(){
        pageNow=1;
        list.clear();
        showProgressDialog();
        getData();
    }
    private void go() {

        Window window;
        window = getActivity().getWindow();
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(window.getDecorView().getWindowToken(), 0);
    }
}

