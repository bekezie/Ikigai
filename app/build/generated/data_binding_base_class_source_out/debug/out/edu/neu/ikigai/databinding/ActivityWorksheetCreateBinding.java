// Generated by view binder compiler. Do not edit!
package edu.neu.ikigai.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

public final class ActivityWorksheetCreateBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button addEventButton;

  @NonNull
  public final TextView addEventtextView;

  @NonNull
  public final Button addThoughtButton;

  @NonNull
  public final TextView addThoughttextView;

  private ActivityWorksheetCreateBinding(@NonNull ConstraintLayout rootView,
      @NonNull Button addEventButton, @NonNull TextView addEventtextView,
      @NonNull Button addThoughtButton, @NonNull TextView addThoughttextView) {
    this.rootView = rootView;
    this.addEventButton = addEventButton;
    this.addEventtextView = addEventtextView;
    this.addThoughtButton = addThoughtButton;
    this.addThoughttextView = addThoughttextView;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityWorksheetCreateBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityWorksheetCreateBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_worksheet_create, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityWorksheetCreateBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.addEventButton;
      Button addEventButton = ViewBindings.findChildViewById(rootView, id);
      if (addEventButton == null) {
        break missingId;
      }

      id = R.id.addEventtextView;
      TextView addEventtextView = ViewBindings.findChildViewById(rootView, id);
      if (addEventtextView == null) {
        break missingId;
      }

      id = R.id.addThoughtButton;
      Button addThoughtButton = ViewBindings.findChildViewById(rootView, id);
      if (addThoughtButton == null) {
        break missingId;
      }

      id = R.id.addThoughttextView;
      TextView addThoughttextView = ViewBindings.findChildViewById(rootView, id);
      if (addThoughttextView == null) {
        break missingId;
      }

      return new ActivityWorksheetCreateBinding((ConstraintLayout) rootView, addEventButton,
          addEventtextView, addThoughtButton, addThoughttextView);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}