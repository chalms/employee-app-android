package controllers;

import java.util.ArrayList;
import java.util.List;
import util.WebClient;
import main.metrics.MainActivity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class LoginController {

	public MainActivity context;
	private final Dialog dialog;

	private OnClickListener loginListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if (authenticated(getPassword(), getUserName())) {
				makeToast("Welcome to the Mobile Dashboard");
				dismissDialog();
				((MainActivity) context).getListViewController()
						.renderListView();
			} else {
				makeToast("Password Unsuccessful!");
			}
		}
	};
	
	private Dialog.OnKeyListener keyListener = new Dialog.OnKeyListener() {
		@Override
		public boolean onKey(DialogInterface arg0, int keyCode, KeyEvent arg2) {
			if (keyCode == KeyEvent.KEYCODE_BACK) {
				String x = getPassword().getText().toString();
				if (x != null) {
					if (x.length() > 0) {
						x = x.substring(0, x.length() - 1);
						getPassword().setText(x);
					}
				}
			}
			return true;
		}
	};

	public LoginController(MainActivity c) {
		this.context = c;
		this.dialog = new Dialog(this.context);
		this.dialog.setContentView(main.firealertapp.R.layout.login);
		this.dialog.setTitle("Login");
		Button btnSignIn = this.getSignInButton();
		btnSignIn.setOnClickListener(loginListener);
		dialog.setOnKeyListener(keyListener);
		System.out.println("Showing Login Controller");
		showDialog();
	}

	private Button getSignInButton() {
		return (Button) this.dialog.findViewById(main.firealertapp.R.id.buttonSignIn);
	}

	public void makeToast(String butter) {
		Toast.makeText(context, butter, Toast.LENGTH_LONG).show();
		return;
	}

	public void dismissDialog() {
		this.dialog.dismiss();
		return;
	}

	public void showDialog() {
		this.dialog.show();
		return;
	}

	public String getUserName() {
		return (String) this.dialog.findViewById(main.firealertapp.R.id.editTextUserNameToLogin);
	}

	public EditText getPassword() {
		return (EditText) this.dialog.findViewById(main.firealertapp.R.id.editTextPasswordToLogin);
	}

	public boolean authenticated(final EditText password, final String username) {
		boolean passwordValid = false;
		try {
			this.context.getMainController().login(username, password.getText().toString());
			passwordValid = true;
		} catch (Exception e) {
			makeToast(e.getMessage());
		}

		if (passwordValid) {
			((MainActivity) context).setCurrentUserName(getUserName());

		}
		return (passwordValid);
	}

}
