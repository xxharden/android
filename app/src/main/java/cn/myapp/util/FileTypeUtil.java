package cn.myapp.util;


import com.gqh.mystudio.R;

public class FileTypeUtil {

	public static int getFileType( String type ) {
		if( "doc".equals( type ) || "dot".equals( type ) || "docx".equals( type ) || "dotx".equals( type ) ) {
			return R.drawable.icon_doc;
		} else if( "txt".equals( type ) || "rtf".equals( type ) ) {
			return R.drawable.icon_xdoc;
		} else if( "pdf".equals( type ) ) {
			return R.drawable.icon_ppt;
		} else if( "xls".equals( type ) || "xlsx".equals( type ) || "xlt".equals( type ) || "xltx".equals( type ) || "csv".equals( type ) ) {
			return R.drawable.icon_excel;
		} else if( "ppt".equals( type ) || "pptx".equals( type ) || "pot".equals( type ) || "potx".equals( type ) ) {
			return R.drawable.icon_ppt;
		} else if( "jpg".equals( type ) || "jpeg".equals( type ) || "png".equals( type ) || "gif".equals( type ) || "bmp".equals( type ) ) {
			return R.drawable.icon_pic;
		} else if( "rar".equals( type ) || "zip".equals( type ) || "7z".equals( type ) ) {
			return R.drawable.icon_rar;
		} else {
			return R.drawable.icon_wz;
		}
	}
}
