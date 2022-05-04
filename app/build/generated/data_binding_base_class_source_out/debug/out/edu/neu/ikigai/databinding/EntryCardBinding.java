// Generated by view binder compiler. Do not edit!
package edu.neu.ikigai.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import edu.neu.ikigai.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class EntryCardBinding implements ViewBinding {
  @NonNull
  private final CardView rootView;

  @NonNull
  public final CardView worksheetEntryCardView;

  @NonNull
  public final TextView worksheetEntryDate;

  @NonNull
  public final TextView worksheetEntryNumber;

  private EntryCardBinding(@NonNull CardView rootView, @NonNull CardView worksheetEntryCardView,
      @NonNull TextView worksheetEntryDate, @NonNull TextView worksheetEntryNumber) {
    this.rootView = rootView;
    this.worksheetEntryCardView = worksheetEntryCardView;
    this.worksheetEntryDate = worksheetEntryDate;
    this.worksheetEntryNumber = worksheetEntryNumber;
  }

  @Override
  @NonNull
  public CardView getRoot() {
    return rootView;
  }

  @NonNull
  public static EntryCardBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static EntryCardBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.entry_card, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static EntryCardBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      CardView worksheetEntryCardView = (CardView) rootView;

      id = R.id.worksheet_entry_date;
      TextView worksheetEntryDate = ViewBindings.findChildViewById(rootView, id);
      if (worksheetEntryDate == null) {
        break missingId;
      }

      id = R.id.worksheet_entry_number;
      TextView worksheetEntryNumber = ViewBindings.findChildViewById(rootView, id);
      if (worksheetEntryNumber == null) {
        break missingId;
      }

      return new EntryCardBinding((CardView) rootView, worksheetEntryCardView, worksheetEntryDate,
          worksheetEntryNumber);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}