package gti350.golfscore.views;

import gti350.golfscore.R;
import gti350.golfscore.views.listeners.OnThumbnailTextItemClickListener;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;

public class ThumbnailTextItemView extends RelativeLayout implements OnClickListener {

	private TextView tvText;
	private ImageView ivThumbnail;
	private int id;
	private OnThumbnailTextItemClickListener listener = null;
	
	public ThumbnailTextItemView(Context context, int id, Drawable drawable, String text) {
		super(context);

		LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.view_thumbnail_text, this, true);
		
		this.id = id;
		
		tvText = (TextView)findViewById(R.id.tvText);
		ivThumbnail = (ImageView)findViewById(R.id.ivThumbnail);
		
		((RelativeLayout)findViewById(R.id.rl)).setOnClickListener(this);
		
		tvText.setText(text);
		ivThumbnail.setImageDrawable(drawable);
	}

	public void setOnThumbnailTextItemClickListener(OnThumbnailTextItemClickListener listener) {
		this.listener = listener;
	}
	
	@Override
	public void onClick(View v) {
		if (listener != null) {
			listener.onThumbnailTextItemClick(this, id);
		}
	}
	
}
