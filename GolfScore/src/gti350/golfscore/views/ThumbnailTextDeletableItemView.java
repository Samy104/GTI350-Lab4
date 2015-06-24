package gti350.golfscore.views;

import gti350.golfscore.R;
import gti350.golfscore.views.listeners.OnThumbnailTextDeletableItemDeleteClickListener;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.view.View.OnClickListener;

public class ThumbnailTextDeletableItemView extends RelativeLayout implements OnClickListener {

	private TextView tvText;
	private ImageView ivThumbnail;
	private ImageView ivDelete;
	private int position;
	private OnThumbnailTextDeletableItemDeleteClickListener listener = null;
	
	public ThumbnailTextDeletableItemView(Context context, int id, Drawable thumbnail, String text, Drawable delete) {
		super(context);

		LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.view_thumbnail_text_deletable, this, true);
		
		tvText = (TextView)findViewById(R.id.tvText);
		ivThumbnail = (ImageView)findViewById(R.id.ivThumbnail);
		ivDelete = (ImageView)findViewById(R.id.ivDelete);
		
		tvText.setText(text);
		ivThumbnail.setImageDrawable(thumbnail);
		ivDelete.setImageDrawable(delete);
		
		ivDelete.setOnClickListener(this);
	}
	
	public String getText() {
		return tvText.getText().toString();
	}

	public void setOnThumbnailTextDeletableItemDeleteClickListener(
			OnThumbnailTextDeletableItemDeleteClickListener listener) {
		this.listener = listener;
	}
	
	@Override
	public void onClick(View v) {
		if (listener != null) {
			if (v == ivDelete) {
				listener.onThumbnailTextDeletableItemDeleteClick(this, position);
			}
		}
	}
	
}
