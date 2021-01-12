package com.example.linrecurrencerelation;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.RVViewHolder> {

    public int len;
    private int type;
    private int[] mData;

    @NonNull
    @Override
    public RVViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);

        return new RVViewHolder(view, new MyCustomEditTextListener());
    }

    @Override
    public void onBindViewHolder(@NonNull RVViewHolder holder, int position) {
        holder.myCustomEditTextListener.updatePosition(holder.getAdapterPosition());
        holder.getEditText().setText(Integer.toString(mData[position]));

        if (type == 1) holder.getTextView().setText("C" + (position + 1) + " = ");
        else holder.getTextView().setText("X" + (position + 1) + " = ");

    }

    public RVAdapter(int len, int[] newData, int type) {
        this.len = len;
        this.mData = newData;
        this.type = type;
    }

    @Override
    public int getItemCount() {
        return len;
    }

    public int[] getData() {
        return mData;
    }

    public static class RVViewHolder extends RecyclerView.ViewHolder {
        private EditText editText;
        private TextView textView;
        public MyCustomEditTextListener myCustomEditTextListener;

        public RVViewHolder(@NonNull View itemView, MyCustomEditTextListener myCustomEditTextListener) {
            super(itemView);

            editText = (EditText) itemView.findViewById(R.id.edit_coeff);
            this.myCustomEditTextListener = myCustomEditTextListener;
            editText.addTextChangedListener(myCustomEditTextListener);
            textView = (TextView) itemView.findViewById(R.id.tv_coeff);

        }

        public EditText getEditText() {
            return editText;
        }

        public TextView getTextView() {
            return textView;
        }
    }

    private class MyCustomEditTextListener implements TextWatcher {
        private int position;

        public void updatePosition(int position) {
            this.position = position;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            // no op
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            if (charSequence == null || charSequence.toString() == null || charSequence.toString().length() == 0) {
                mData[position] = 0;
            }
            else mData[position] = Integer.parseInt(charSequence.toString());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }

}
