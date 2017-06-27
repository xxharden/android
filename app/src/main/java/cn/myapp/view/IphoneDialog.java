package cn.myapp.view;



import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface.OnDismissListener;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.gqh.mystudio.R;


public class IphoneDialog
{
    public TextView tv;
    private View view;
    private Dialog dialog;

    public IphoneDialog(Context context)
    {
        view = LayoutInflater.from(context).inflate(R.layout.dialog, null);
        tv = (TextView) view.findViewById(R.id.textinfo);
        dialog = new Dialog(context, R.style.IphoneDialogTheme);
        dialog.setContentView(view);
        
        this.setCancelable(false);
    }

    public void setCancelable(boolean flag){
    	if(dialog != null){
    		dialog.setCancelable(flag);
    	}
    }
    
    public void dismiss()
    {
        if (dialog != null)
        {
            dialog.dismiss();
        }
        else
        {
            throw new NullPointerException("自定义 dialog 为null，原因可能是没有初始化！");
        }
    }

    public void show()
    {
        if (dialog != null && !dialog.isShowing())
        {
            dialog.show();
        }
    }

    public void setMessage(String message)
    {
        if (tv != null)
        {
            tv.setText(message);
        }
        else
        {
            throw new NullPointerException("自定义 dialog 中textview 为null，原因可能是没有初始化！");
        }

    }

    public boolean isShowing()
    {
        return dialog.isShowing();
    }

    public void setOnDismissListener(OnDismissListener dismissListener)
    {
        dialog.setOnDismissListener(dismissListener);
        
    }

    public void cancel(){
    	dialog.cancel();
    }
}
