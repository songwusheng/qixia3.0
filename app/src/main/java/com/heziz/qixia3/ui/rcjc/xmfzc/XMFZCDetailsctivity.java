package com.heziz.qixia3.ui.rcjc.xmfzc;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.heziz.qixia3.R;
import com.heziz.qixia3.adaper.xmjc.ImagePickerAdapter;
import com.heziz.qixia3.adaper.xmjc.MyGridViewAdapter;
import com.heziz.qixia3.app.MyApplication;
import com.heziz.qixia3.base.BaseActivity;
import com.heziz.qixia3.bean.UserInfor;
import com.heziz.qixia3.bean.rcjc.ChangeStatusBean;
import com.heziz.qixia3.bean.rcjc.CheckDetailsBean;
import com.heziz.qixia3.image.ImagePreviewActivity;
import com.heziz.qixia3.network.API;
import com.heziz.qixia3.network.JsonCallBack1;
import com.heziz.qixia3.network.OkGoClient;
import com.heziz.qixia3.network.SRequstBean;
import com.heziz.qixia3.utils.GlideImageLoader;
import com.heziz.qixia3.utils.StringUtil;
import com.heziz.qixia3.utils.TimeUtils;
import com.heziz.qixia3.utils.ToastUtil;
import com.heziz.qixia3.view.MyGridView;
import com.heziz.qixia3.view.SelectDialog;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.ui.ImagePreviewDelActivity;
import com.lzy.imagepicker.view.CropImageView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 项目方的自查列表-->>检查记录详情
 */
public class XMFZCDetailsctivity extends BaseActivity implements ImagePickerAdapter.OnRecyclerViewItemClickListener, View.OnClickListener {
    public static final int IMAGE_ITEM_ADD = -1;
    public static final int REQUEST_CODE_SELECT = 100;
    public static final int REQUEST_CODE_PREVIEW = 101;

    @BindView(R.id.myGridView)
    MyGridView goodsGridView;
    MyGridViewAdapter mAdapter;

    @BindView(R.id.rlBack)
    RelativeLayout rlBack;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvProName)
    TextView tvProName;
    @BindView(R.id.tvJCRy)
    TextView tvJCRy;
    @BindView(R.id.tvJCTime)
    TextView tvJCTime;

    @BindView(R.id.tvJCTitle)
    TextView tvJCTitle;
    @BindView(R.id.tvJCStatus)
    TextView tvJCStatus;
    @BindView(R.id.etJCDes)
    EditText etJCDes;
    @BindView(R.id.recyclerViewJC)
    RecyclerView recyclerViewJC;


    @BindView(R.id.llFCJG)
    LinearLayout llFCJG;
    @BindView(R.id.llFCJG1)
    LinearLayout llFCJG1;
    @BindView(R.id.llFCJG2)
    LinearLayout llFCJG2;
    @BindView(R.id.tvFCRy)
    TextView tvFCRy;
    @BindView(R.id.tvFCTime)
    TextView tvFCTime;
    @BindView(R.id.etFCDes)
    EditText etFCDes;
    @BindView(R.id.tvFCStatus)
    TextView tvFCStatus;
    @BindView(R.id.recyclerViewFC)
    RecyclerView recyclerViewFC;

    @BindView(R.id.rg)
    RadioGroup rg;
    @BindView(R.id.rb1)
    RadioButton rb1;
    @BindView(R.id.btnSubmit)
    Button btnSubmit;

    private Dialog dialog;
    private ChangeStatusBean changeStatusBean;

    private ImagePickerAdapter adapter;
    private ArrayList<ImageItem> selImageList = new ArrayList<>(); //当前选择的所有图片
    private ImagePickerAdapter adapter1;
    private ArrayList<ImageItem> selImageList1 = new ArrayList<>(); //当前选择的所有图片
    private int maxImgCount = 9;               //允许选择图片最大数
    private String[] goods = new String[]{"道路保洁", "裸露地面覆盖", "工地围挡", "道路硬化", "出入口冲洗", "油品管理", "工程机械", "渣土运输管理", "责任公示牌"};
    private String[] classs = new String[]{"Cleaning","Coverage","Enclosure","Hardening","Inout","Oils","Engine","Transport","Publicity"};
    private List<String> statusList = new ArrayList<>();
    private CheckDetailsBean checkDetailsBean;

    private int type;

    private int id;

    private int num;

    private StringBuffer stringBuffer=new StringBuffer();



    private int currentPosition;

    private UserInfor userInfor;
    private String checkname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xmf_zc_details);

        ButterKnife.bind(this);
        initViews();
        initDatas();
        initListeners();

    }

    private void initViews() {
        userInfor=MyApplication.getInstance().getUserInfor();
        type = getIntent().getIntExtra("type", 0);
        id = getIntent().getIntExtra("id", 0);
        checkname = getIntent().getStringExtra("checkname");

        tvJCRy.setText(checkname);
        tvFCRy.setText(checkname);
        if (type == 1) {
            tvTitle.setText("检查记录");
        } else if (type == 2) {
            tvTitle.setText("待复查");
        }
        //复查结果默认选中合格
        rg.check(R.id.rb1);
        initImagePicker();
        //巡检图片适配器初始化
        adapter = new ImagePickerAdapter(this, selImageList, 0, 1);
        adapter.setOnItemClickListener(this);
        recyclerViewJC.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerViewJC.setHasFixedSize(true);
        recyclerViewJC.setAdapter(adapter);

        adapter1 = new ImagePickerAdapter(this, selImageList1, 0, 2);
        adapter1.setOnItemClickListener(this);
        recyclerViewFC.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerViewFC.setHasFixedSize(true);
        recyclerViewFC.setAdapter(adapter1);

        for (int i = 0; i < 9; i++) {
            statusList.add(getStatus(0));
        }
        mAdapter = new MyGridViewAdapter(XMFZCDetailsctivity.this);
        mAdapter.setData(goods, 0, statusList);//传数组, 并指定默认值
        goodsGridView.setAdapter(mAdapter);

        getDetails();

    }

    private void initImagePicker() {
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new GlideImageLoader());   //设置图片加载器
        imagePicker.setShowCamera(true);                      //显示拍照按钮
        imagePicker.setCrop(false);                           //允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(true);                   //是否按矩形区域保存
        imagePicker.setSelectLimit(maxImgCount);              //选中数量限制
        //imagePicker.setSelectedImages(selImageList);
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
        imagePicker.setFocusWidth(800);                       //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(800);                      //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(1000);                         //保存文件的宽度。单位像素
        imagePicker.setOutPutY(1000);                         //保存文件的高度。单位像素
    }

    private void getDetails() {
        String url1 = API.XMF_CHECK_DETAILS + "?access_token=" + MyApplication.getInstance().getUserInfor().getUuid();
        Map<String, String> params1 = new HashMap<>();
        params1.put("id", id + "");
        JsonCallBack1<SRequstBean<CheckDetailsBean>> jsonCallBack1 = new JsonCallBack1<SRequstBean<CheckDetailsBean>>() {
            @Override
            public void onSuccess(com.lzy.okgo.model.Response<SRequstBean<CheckDetailsBean>> response) {
                dissmissProgressDialog();
                if (response.body().getData() != null) {
                    //ToastUtil.showToast("获取详情成功");
                    checkDetailsBean = response.body().getData();
                    ////接收上个界面传过来的检查记录详情类  更新界面
                    //checkDetailsBean = (CheckDetailsBean) getIntent().getSerializableExtra("bean");
                    tvProName.setText(checkDetailsBean.getProjectName());
                    //tvJCRy.setText(checkDetailsBean.getWgName());
                    tvJCTime.setText(checkDetailsBean.getCreateTime());
                    refreshImage(checkDetailsBean.getCleaningImage());
                    refreshImage1(checkDetailsBean.getChangeCleaningImage());

                    statusList.set(0, getStatus(checkDetailsBean.getCleaningStatus()));
                    statusList.set(1, getStatus(checkDetailsBean.getCoverageStatus()));
                    statusList.set(2, getStatus(checkDetailsBean.getEnclosureStatus()));
                    statusList.set(3, getStatus(checkDetailsBean.getHardeningStatus()));
                    statusList.set(4, getStatus(checkDetailsBean.getInoutStatus()));
                    statusList.set(5, getStatus(checkDetailsBean.getOilsStatus()));
                    statusList.set(6, getStatus(checkDetailsBean.getEngineStatus()));
                    statusList.set(7, getStatus(checkDetailsBean.getTransportStatus()));
                    statusList.set(8, getStatus(checkDetailsBean.getPublicityStatus()));
                    mAdapter.setSeclection(currentPosition);
                    mAdapter.notifyDataSetChanged();

                    chengeStatus(currentPosition);
                    ////默认显示道路保洁
                    //tvJCTitle.setText("道路保洁");
                    //etJCDes.setText(checkDetailsBean.getCleaningCommonts());
                    //tvJCStatus.setText(getStatus1(checkDetailsBean.getCleaningStatus()));

                }

            }

            @Override
            public void onError(com.lzy.okgo.model.Response<SRequstBean<CheckDetailsBean>> response) {
                super.onError(response);
                //adapter.loadMoreFail();
                dissmissProgressDialog();
            }

        };
        OkGoClient.getInstance()
                .getJsonData1(url1, params1, jsonCallBack1);
    }

    private void initDatas() {

    }

    private void initListeners() {
        btnSubmit.setOnClickListener(this);
        rlBack.setOnClickListener(this);
        goodsGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,
                                    int position, long arg3) {
                int  sb_length = stringBuffer.length();// 取得字符串的长度
                stringBuffer.delete(0,sb_length);
                num=0;
                currentPosition=position;
                mAdapter.setSeclection(position);//传值更新
                mAdapter.notifyDataSetChanged();

                chengeStatus(position);

            }
        });

    }

    private void chengeStatus(int position){
        if (statusList.get(position).equals("待复查")) {
            llFCJG.setVisibility(View.VISIBLE);
            llFCJG1.setVisibility(View.GONE);
            llFCJG2.setVisibility(View.VISIBLE);
            selImageList1.clear();
            adapter1 = new ImagePickerAdapter(XMFZCDetailsctivity.this, selImageList1, maxImgCount, 2);
            adapter1.setOnItemClickListener(XMFZCDetailsctivity.this);
            adapter1.setImages(selImageList1);
            recyclerViewFC.setAdapter(adapter1);
            etFCDes.setEnabled(true);
            etFCDes.setText("");
            setDatas(position,2);
        } else if (statusList.get(position).equals("复查合格")){
            llFCJG.setVisibility(View.VISIBLE);
            llFCJG1.setVisibility(View.VISIBLE);
            llFCJG2.setVisibility(View.GONE);
            adapter1 = new ImagePickerAdapter(XMFZCDetailsctivity.this, selImageList1, 0, 2);
            adapter1.setOnItemClickListener(XMFZCDetailsctivity.this);
            recyclerViewFC.setAdapter(adapter1);
            setDatas(position,3);
            etFCDes.setEnabled(false);
        }else{
            llFCJG.setVisibility(View.GONE);
            setDatas(position,1);
        }
    }

    private void setDatas(int position,int type) {
        String imgs = null;
        String imgs1 = null;
        switch (position) {
            case 0:
                tvJCTitle.setText("道路保洁");
                tvJCStatus.setText(getStatus(checkDetailsBean.getCleaningStatus()));
                etJCDes.setText(checkDetailsBean.getCleaningCommonts());
                imgs = checkDetailsBean.getCleaningImage();
                if(type==3){
                    imgs1 = checkDetailsBean.getChangeCleaningImage();
                    etFCDes.setText(checkDetailsBean.getChangeCleaningCommonts());
                    tvFCTime.setText(checkDetailsBean.getChangeCleaningTime());
                    tvFCStatus.setText(getStatus(checkDetailsBean.getCleaningStatus()));
                }

                //tvZGTime.setText(checkDetailsBean.getChangeCleaningTime());

            break;
            case 1:
                tvJCTitle.setText("裸露地面覆盖");
                tvJCStatus.setText(getStatus(checkDetailsBean.getCoverageStatus()));
                etJCDes.setText(checkDetailsBean.getCoverageCommonts());
                imgs = checkDetailsBean.getCoverageImage();
                if (type==3){
                    imgs1 = checkDetailsBean.getChangeCoverageImage();
                    etFCDes.setText(checkDetailsBean.getChangeCoverageCommonts());
                    tvFCTime.setText(checkDetailsBean.getChangeCoverageTime());
                    tvFCStatus.setText(getStatus(checkDetailsBean.getCoverageStatus()));
                }
                //tvZGTime.setText(checkDetailsBean.getChangeCoverageTime());
                break;
            case 2:
                tvJCTitle.setText("工地围挡");
                tvJCStatus.setText(getStatus(checkDetailsBean.getEnclosureStatus()));
                etJCDes.setText(checkDetailsBean.getEnclosureCommonts());
                imgs = checkDetailsBean.getEnclosureImage();
                if (type==3) {
                    imgs1 = checkDetailsBean.getChangeEnclosureImage();
                    etFCDes.setText(checkDetailsBean.getChangeEnclosureCommonts());
                    tvFCTime.setText(checkDetailsBean.getChangeEnclosureTime());
                    tvFCStatus.setText(getStatus(checkDetailsBean.getEnclosureStatus()));
                }
                //tvZGTime.setText(checkDetailsBean.getChangeEnclosureTime());
                break;
            case 3:
                tvJCTitle.setText("道路硬化");
                tvJCStatus.setText(getStatus(checkDetailsBean.getHardeningStatus()));
                etJCDes.setText(checkDetailsBean.getHardeningCommonts());
                imgs = checkDetailsBean.getHardeningImage();
                if (type==3) {
                    imgs1 = checkDetailsBean.getChangeHardeningImage();
                    etFCDes.setText(checkDetailsBean.getChangeHardeningCommonts());
                    tvFCTime.setText(checkDetailsBean.getChangeHardeningTime());
                    tvFCStatus.setText(getStatus(checkDetailsBean.getHardeningStatus()));
                }
                //tvZGTime.setText(checkDetailsBean.getChangeHardeningTime());
                break;
            case 4:
                tvJCTitle.setText("出入口冲洗");
                tvJCStatus.setText(getStatus(checkDetailsBean.getInoutStatus()));
                etJCDes.setText(checkDetailsBean.getInoutCommonts());
                imgs = checkDetailsBean.getInoutImage();
                if (type==3) {
                    imgs1 = checkDetailsBean.getChangeInoutImage();
                    etFCDes.setText(checkDetailsBean.getChangeInoutCommonts());
                    tvFCTime.setText(checkDetailsBean.getChangeInoutTime());
                    tvFCStatus.setText(getStatus(checkDetailsBean.getInoutStatus()));
                }
                //tvZGTime.setText(checkDetailsBean.getChangeInoutTime());
                break;
            case 5:
                tvJCTitle.setText("油品管理");
                tvJCStatus.setText(getStatus(checkDetailsBean.getOilsStatus()));
                etJCDes.setText(checkDetailsBean.getOilsCommonts());
                imgs = checkDetailsBean.getOilsImage();
                if (type==3) {
                    imgs1 = checkDetailsBean.getChangeOilsImage();
                    etFCDes.setText(checkDetailsBean.getChangeOilsCommonts());
                    tvFCTime.setText(checkDetailsBean.getChangeOilsTime());
                    tvFCStatus.setText(getStatus(checkDetailsBean.getOilsStatus()));
                }
                //tvZGTime.setText(checkDetailsBean.getChangeOilsTime());
                break;
            case 6:
                tvJCTitle.setText("工程机械");
                tvJCStatus.setText(getStatus(checkDetailsBean.getEngineStatus()));
                etJCDes.setText(checkDetailsBean.getEngineCommonts());
                imgs = checkDetailsBean.getEngineImage();
                if (type==3) {
                    imgs1 = checkDetailsBean.getChangeEngineImage();
                    etFCDes.setText(checkDetailsBean.getChangeEngineCommonts());
                    tvFCTime.setText(checkDetailsBean.getChangeEngineTime());
                    tvFCStatus.setText(getStatus(checkDetailsBean.getEngineStatus()));
                }
                //tvZGTime.setText(checkDetailsBean.getChangeEngineTime());
                break;
            case 7:
                tvJCTitle.setText("渣土运输管理");
                tvJCStatus.setText(getStatus(checkDetailsBean.getTransportStatus()));
                etJCDes.setText(checkDetailsBean.getTransportCommonts());
                imgs = checkDetailsBean.getTransportImage();
                if (type==3) {
                    imgs1 = checkDetailsBean.getChangeTransportImage();
                    etFCDes.setText(checkDetailsBean.getChangeTransportCommonts());
                    tvFCTime.setText(checkDetailsBean.getChangeTransportTime());
                    tvFCStatus.setText(getStatus(checkDetailsBean.getTransportStatus()));
                }
                //tvZGTime.setText(checkDetailsBean.getChangeTransportTime());
                break;
            case 8:
                tvJCTitle.setText("责任公示牌");
                tvJCStatus.setText(getStatus(checkDetailsBean.getPublicityStatus()));
                etJCDes.setText(checkDetailsBean.getPublicityCommonts());
                imgs = checkDetailsBean.getPublicityImage();
                if (type==3) {
                    imgs1 = checkDetailsBean.getChangePublicityImage();
                    etFCDes.setText(checkDetailsBean.getChangePublicityCommonts());
                    tvFCTime.setText(checkDetailsBean.getChangePublicityTime());
                    tvFCStatus.setText(getStatus(checkDetailsBean.getPublicityStatus()));
                }
                //tvZGTime.setText(checkDetailsBean.getChangePublicityTime());
                break;
        }
        refreshImage(imgs);
        if (type==3) {
            refreshImage1(imgs1);
        }
    }

    private void refreshImage(String imgURL) {
        selImageList.clear();
        if (!StringUtil.isNull(imgURL)) {
            List<String> imgs = Arrays.asList(imgURL.split(","));
            selImageList.clear();
            if (imgs.size() != 0) {
                for (int i = 0; i < imgs.size(); i++) {
                    ImageItem item = new ImageItem();
                    item.setPath(imgs.get(i));
                    selImageList.add(item);
                }
            }
        }
        adapter.setImages(selImageList);

    }

    private void refreshImage1(String imgURL) {
        selImageList1.clear();
        if (!StringUtil.isNull(imgURL)) {
            List<String> imgs = Arrays.asList(imgURL.split(","));

            if (imgs.size() != 0) {
                for (int i = 0; i < imgs.size(); i++) {
                    ImageItem item = new ImageItem();
                    item.setPath(imgs.get(i));
                    selImageList1.add(item);
                }
            }
        }
        adapter1.setImages(selImageList1);

    }

    private String getStatus(int status) {
        String ss = "";
        switch (status) {
            case 1:
                ss = "合格";
                break;
            case 2:
                ss = "待复查";
                break;
            case 4:
                ss = "复查合格";
                break;
            default: {
                ss = "审核中";
            }

        }
        return ss;
    }

    private String getStatus1(int status) {
        String ss = "";
        switch (status) {
            case 1:
                ss = "未检查";
                break;
            case 2:
                ss = "合格";
                break;
            case 3:
                ss = "不合格";
                break;
            case 4:
                ss = "通知整改";
                break;
            case 5:
                ss = "整改待确认";
                break;
            case 6:
                ss = "整改通过";
                break;
            case 7:
                ss = "整改不通过";
                break;
        }
        return ss;
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rlBack:
                finish();
                break;
            case R.id.btnSubmit:
                if(selImageList1.size()==0){
                    ToastUtil.showToast("至少上传一张图片");
                    return;
                }
                showProgressDialog();
                uploadPicture();
                break;
        }
    }

    private void setFCJG() {
        String str=stringBuffer.toString();
        Map<String,String> params1=new HashMap<>();
        params1.put("id",checkDetailsBean.getId()+"");
        params1.put("change"+classs[currentPosition]+"Account",userInfor.getName());
        params1.put("change"+classs[currentPosition]+"Commonts",etFCDes.getText().toString());
        params1.put("change"+classs[currentPosition]+"Image",str.substring(0,str.length()-1));
        params1.put("change"+classs[currentPosition]+"Time",TimeUtils.getCurrentTime());
        params1.put(classs[currentPosition].toLowerCase()+"Status",rb1.isChecked()?"4":"2");
        String url1 = API.XMF_CHECK_FC + "?access_token=" + MyApplication.getInstance().getUserInfor().getUuid();
        JsonCallBack1<SRequstBean<String>> jsonCallBack1 = new JsonCallBack1<SRequstBean<String>>() {
            @Override
            public void onSuccess(com.lzy.okgo.model.Response<SRequstBean<String>> response) {
                //ToastUtil.showToast("更改成功");
                //dissmissProgressDialog();
                //Intent intent = new Intent();
                //setResult(100, intent);
                //finish();
                getDetails();
            }

            @Override
            public void onError(com.lzy.okgo.model.Response<SRequstBean<String>> response) {
                super.onError(response);
                //adapter.loadMoreFail();
                dissmissProgressDialog();
            }

        };
        OkGoClient.getInstance()
                .postJsonData10(url1, params1, jsonCallBack1);
    }

    private SelectDialog showDialog(SelectDialog.SelectDialogListener listener, List<String> names) {
        SelectDialog dialog = new SelectDialog(this, R.style
                .transparentFrameWindowStyle,
                listener, names);
        if (!this.isFinishing()) {
            dialog.show();
        }
        return dialog;
    }

    @Override
    public void onItemClick(View view, int position, int num) {
        switch (position) {
            case IMAGE_ITEM_ADD:
                List<String> names = new ArrayList<>();
                names.add("拍照");
                names.add("相册");
                showDialog(new SelectDialog.SelectDialogListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        switch (position) {
                            case 0: // 直接调起相机
                                /**
                                 * 0.4.7 目前直接调起相机不支持裁剪，如果开启裁剪后不会返回图片，请注意，后续版本会解决
                                 *
                                 * 但是当前直接依赖的版本已经解决，考虑到版本改动很少，所以这次没有上传到远程仓库
                                 *
                                 * 如果实在有所需要，请直接下载源码引用。
                                 */
                                //打开选择,本次允许选择的数量
                                ImagePicker.getInstance().setSelectLimit(maxImgCount - selImageList1.size());
                                Intent intent = new Intent(XMFZCDetailsctivity.this, ImageGridActivity.class);
                                intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
                                startActivityForResult(intent, REQUEST_CODE_SELECT);
                                break;
                            case 1:
                                //打开选择,本次允许选择的数量
                                ImagePicker.getInstance().setSelectLimit(maxImgCount - selImageList1.size());
                                Intent intent1 = new Intent(XMFZCDetailsctivity.this, ImageGridActivity.class);
                                /* 如果需要进入选择的时候显示已经选中的图片，
                                 * 详情请查看ImagePickerActivity
                                 * */
//                                intent1.putExtra(ImageGridActivity.EXTRAS_IMAGES,images);
                                startActivityForResult(intent1, REQUEST_CODE_SELECT);
                                break;
                            default:
                                break;
                        }

                    }
                }, names);
                break;
            default:
                //打开预览
                Intent intentPreview = new Intent();
                if (num == 1) {
                    intentPreview.setClass(XMFZCDetailsctivity.this,ImagePreviewActivity.class);
                    intentPreview.putExtra(ImagePicker.EXTRA_IMAGE_ITEMS, (ArrayList<ImageItem>) adapter.getImages());
                } else {
                    if(statusList.get(currentPosition).equals("复查合格")){
                        intentPreview.setClass(XMFZCDetailsctivity.this,ImagePreviewActivity.class);
                    }else{
                        intentPreview.setClass(XMFZCDetailsctivity.this, ImagePreviewDelActivity.class);
                    }
                    intentPreview.putExtra(ImagePicker.EXTRA_IMAGE_ITEMS, (ArrayList<ImageItem>) adapter1.getImages());
                }

                intentPreview.putExtra(ImagePicker.EXTRA_SELECTED_IMAGE_POSITION, position);
                intentPreview.putExtra(ImagePicker.EXTRA_FROM_ITEMS, true);
                startActivityForResult(intentPreview, REQUEST_CODE_PREVIEW);
                break;
        }
    }

    ArrayList<ImageItem> images = null;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            //添加图片返回
            if (data != null && requestCode == REQUEST_CODE_SELECT) {
                images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                if (images != null) {
                    selImageList1.addAll(images);
                    adapter1.setImages(selImageList1);
                }
            }
        } else if (resultCode == ImagePicker.RESULT_CODE_BACK) {
            //预览图片返回
            if (data != null && requestCode == REQUEST_CODE_PREVIEW) {
                images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_IMAGE_ITEMS);
                if (images != null) {
                    selImageList1.clear();
                    selImageList1.addAll(images);
                    adapter1.setImages(selImageList1);
                }
            }
        }
    }

    private void uploadPicture() {
//        //上传单个文件
        String url = API.IMAGE_FILE_UPLOAD;
//    File  file = new File(file.getAbsolutePath(), file.getName());
        List<File> list1=new ArrayList<>();
        for(int i=0;i<selImageList1.size();i++){
            //list1.add(new File(selImageList.get(i).path));
            File file=new File(selImageList1.get(i).path);
            OkGo.<String>post(url)
                    .tag(this)
                    .params("file", file)
                    .isMultipart(true)
                    .execute(new StringCallback() {
                        @Override
                        public void onSuccess(Response<String> response) {
                            num++;
                            ToastUtil.showToast("上传成功"+num);
                            String res=response.body();
                            String[] ress=res.split(",");
                            String a=ress[2].replace("\"","");
                            String b=ress[1].replace("\"","");
                            Log.e("main","上传成功" + a+b);
                            StringBuffer string=new StringBuffer();
                            string.append(a+b);
                            string.append(",");
                            stringBuffer.append(string.toString());

                            if(num==selImageList1.size()){
                                Log.w("main","上传成功:" + stringBuffer.toString());
                                setFCJG();
                            }


                        }

                        @Override
                        public void onError(Response<String> response) {
//                            Log.e("main","上传失败" + response.body());
                            ToastUtil.showToast("上传失败");
                            dissmissProgressDialog();
                        }
                    });
        }

    }

}
