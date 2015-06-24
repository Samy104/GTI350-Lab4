package gti350.golfscore.views;

import gti350.golfscore.R;
import gti350.golfscore.views.listeners.OnPlusMinusItemValueChangedListener;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.view.View.OnClickListener;

public class PlusMinusItemView extends RelativeLayout implements OnClickListener {

	private ImageButton btnPlus;
	private ImageButton btnMinus;
	private TextView tvText;
	private TextView tvValue;
	private int value;
	private int id;
	private int min;
	private int max;
	private OnPlusMinusItemValueChangedListener listener = null;
	
	public PlusMinusItemView(Context context, int id, String text, int value, int min, int max) {
		super(context);
		
		this.id = id;
		this.min = min;
		this.max = max;
		this.value = value;
		
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.view_item_plus_minus, this, true);
		
		btnPlus = (ImageButton)findViewById(R.id.imageButtonPlus);
		btnMinus = (ImageButton)findViewById(R.id.imageButtonMinus);
		tvValue = (TextView)findViewById(R.id.tvValue);
		tvText = (TextView)findViewById(R.id.tvText);
		
		this.setText(text);
		this.setValue(value);
		
		btnPlus.setOnClickListener(this);
		btnMinus.setOnClickListener(this);
	}
	
	public void setOnValueChangedListener(OnPlusMinusItemValueChangedListener listener) {
		this.listener = listener;
	}

	/**
	 * Sets the text on the left.
	 * 
	 * @param text
	 */
	public void setText(String text) {
		tvText.setText(text);
	}
	
	/**
	 * Sets the current value if it is within boundaries.
	 * 
	 * @param value
	 * @return true if value changed, otherwise false.
	 */
	public boolean setValue(int value) {
		if (value >= min && value <= max) {
			this.value = value;
			tvValue.setText(Integer.toString(value));
			setButtonsVisibility();
			return true;
		}
		
		return false;
	}
	
	@Override
	public void onClick(View v) {
		if (listener != null) {
			if (v == btnPlus) {
				if (setValue(value + 1)) {
					listener.onPlusMinusItemValueChanged(this, id, value);
				}
			}
			else if (v == btnMinus) {
				if (setValue(value - 1)) {
					listener.onPlusMinusItemValueChanged(this, id, value);
				}
			}
		}
	}
	
	/**
	 * Sets a button invisible if the min or max is reached.
	 */
	private void setButtonsVisibility() {
		if (value == min) {
			btnMinus.setVisibility(View.INVISIBLE);
		}
		else {
			btnMinus.setVisibility(View.VISIBLE);
		}
		
		if (value == max) {
			btnPlus.setVisibility(View.INVISIBLE);
		}
		else {
			btnPlus.setVisibility(View.VISIBLE);
		}
	}
	
}
