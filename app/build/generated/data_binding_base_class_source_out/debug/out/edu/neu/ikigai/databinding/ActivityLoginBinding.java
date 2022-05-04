// Generated by view binder compiler. Do not edit!
package edu.neu.ikigai.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import edu.neu.ikigai.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityLoginBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final EditText etEmail;

  @NonNull
  public final EditText etPassword;

  @NonNull
  public final TextView forgot;

  @NonNull
  public final TextView log;

  @NonNull
  public final ProgressBar progressBar;

  @NonNull
  public final TextView register;

  @NonNull
  public final Button submit;

  @NonNull
  public final TextView title;

  private ActivityLoginBinding(@NonNull ConstraintLayout rootView, @NonNull EditText etEmail,
      @NonNull EditText etPassword, @NonNull TextView forgot, @NonNull TextView log,
      @NonNull ProgressBar progressBar, @NonNull TextView register, @NonNull Button submit,
      @NonNull TextView title) {
    this.rootView = rootView;
    this.etEmail = etEmail;
    this.etPassword = etPassword;
    this.forgot = forgot;
    this.log = log;
    this.progressBar = progressBar;
    this.register = register;
    this.submit = submit;
    this.title = title;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityLoginBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityLoginBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_login, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityLoginBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.et_email;
      EditText etEmail = ViewBindings.findChildViewById(rootView, id);
      if (etEmail == null) {
        break missingId;
      }

      id = R.id.et_password;
      EditText etPassword = ViewBindings.findChildViewById(rootView, id);
      if (etPassword == null) {
        break missingId;
      }

      id = R.id.forgot;
      TextView forgot = ViewBindings.findChildViewById(rootView, id);
      if (forgot == null) {
        break missingId;
      }

      id = R.id.log;
      TextView log = ViewBindings.findChildViewById(rootView, id);
      if (log == null) {
        break missingId;
      }

      id = R.id.progress_bar;
      ProgressBar progressBar = ViewBindings.findChildViewById(rootView, id);
      if (progressBar == null) {
        break missingId;
      }

      id = R.id.register;
      TextView register = ViewBindings.findChildViewById(rootView, id);
      if (register == null) {
        break missingId;
      }

      id = R.id.submit;
      Button submit = ViewBindings.findChildViewById(rootView, id);
      if (submit == null) {
        break missingId;
      }

      id = R.id.title;
      TextView title = ViewBindings.findChildViewById(rootView, id);
      if (title == null) {
        break missingId;
      }

      return new ActivityLoginBinding((ConstraintLayout) rootView, etEmail, etPassword, forgot, log,
          progressBar, register, submit, title);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}