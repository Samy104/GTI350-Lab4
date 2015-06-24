package gti350.golfscore.utils;

import android.graphics.drawable.Drawable;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.View.OnTouchListener;
import android.widget.EditText;

/**
 * Helper class to encapsulate the logic of a
 * text clear button in an editable text field.
 * 
 * TODO: This sucks.
 * 
 * @author Simon RG
 */
public class TextClearButtonHelper {

	/**
	 * Helper function to add a clear button, as defined by
	 * Jenifer Tidwill in her book, to an editable text field.
	 * 
	 * @param editText Text field in need of a text clear button.
	 * @param drawable Image of the button.
	 */
	public static void set(final EditText editText, final Drawable drawable) {
		set(editText, drawable, null);
	}
	
	/**
	 * Helper function to add a clear button, as defined by
	 * Jenifer Tidwill in her book, to an editable text field.
	 * 
	 * When text is cleared, onKey event is fired.
	 * 
	 * Inspired by
	 * http://murali6060.blogspot.ca/2012/03/edit-text-with-clear-button-at-right.html
	 * 
	 * @param editText Text field in need of a text clear button.
	 * @param drawable Image of the button.
	 * @param listener OnKeyListener interface handling this editable text field.
	 */
	public static void set(final EditText editText, final Drawable drawable, final OnKeyListener listener) {
		Drawable[] compounds = editText.getCompoundDrawables();
		drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
		editText.setCompoundDrawables(compounds[0], compounds[1], drawable, compounds[3]);

		editText.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				if(arg1.getX() > editText.getWidth() - drawable.getIntrinsicWidth() -10 ) {
					if(editText.getText().length() > 0) {
						editText.setText("");
						
						if (listener != null) {
							// Additionally to resetting the text, an onKey event is fired.
							// This event is a onKeyDown backspace, but it probably doesn't matter.
							listener.onKey(editText, KeyEvent.KEYCODE_BACK, new KeyEvent(
									KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_BACK));
						}
					}
				}
				
				return false;
			}
		});
	}
	
}
