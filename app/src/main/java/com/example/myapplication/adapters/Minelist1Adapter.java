package com.example.myapplication.adapters;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.myapplication.R;
import com.example.myapplication.po.MineList1Bean;
import com.xq.fasterdialog.bean.InputBean;
import com.xq.fasterdialog.dialog.EditDialog;
import com.xq.fasterdialog.dialog.base.BaseDialog;

import java.util.List;

public class Minelist1Adapter extends BaseAdapter{
    private List<MineList1Bean> listBeans;
    private Context context;


    public Minelist1Adapter(Context context, List<MineList1Bean> listBeans){
        this.context = context;
        this.listBeans = listBeans;

    }
    @Override
    public int getCount() {
        return listBeans.size();
    }

    @Override
    public MineList1Bean getItem(int position) {
        return listBeans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHandler handler;
        if(convertView == null) {
            convertView = LayoutInflater.from(context)
                    .inflate(R.layout.mine_list_1_item, parent, false);
            handler = new ViewHandler();
            handler.icon = convertView.findViewById(R.id.icon);
            handler.title = convertView.findViewById(R.id.title);
            handler.content = convertView.findViewById(R.id.content);
            handler.imageView = convertView.findViewById(R.id.imageView);
            convertView.setTag(handler);
        } else {
            handler = (ViewHandler) convertView.getTag();
        }
        handler.icon.setImageResource(listBeans.get(position).getIcon());
        handler.title.setText(listBeans.get(position).getTitle());
        handler.imageView.setImageResource(R.drawable.right_arrows);
        handler.content.setText(listBeans.get(position).getContent());
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setEditDialog(listBeans.get(position).getTitle(),listBeans.get(position).getContent(),position);
            }
        });
        return convertView;
    }
    public List<MineList1Bean> getListBeans(){
        return listBeans;
    }
    private void setEditDialog( String title, String hint, final int position){
        final EditDialog editDialog = new EditDialog(context);
        InputBean inputBean = new InputBean();
        editDialog.setCustomView(R.layout.layout_editdialog_xq);
        editDialog.setStyle(R.style.Animate_Alpha);
        editDialog.setWidthPercent(0.8f);
        editDialog.setCanceledOnTouchOutside(false);
        editDialog.setPositiveText("确定");
        editDialog.setNegativeText("取消");
        inputBean.setHint(hint);
        editDialog.setTitle(title);
        editDialog.setInputBean(0,inputBean);
        editDialog.show();
        editDialog.setPositiveListener(new BaseDialog.OnDialogClickListener(){
            @Override
            public void onClick(BaseDialog dialog) {
                SparseArray<CharSequence> array = editDialog.getTextArray();
                String message = (String)array.get(0);
                listBeans.get(position).setContent(message);
                notifyDataSetChanged();
            }
        });
    }

    class ViewHandler{
        ImageView icon;
        TextView title;
        TextView content;
        ImageView imageView;
    }
}
