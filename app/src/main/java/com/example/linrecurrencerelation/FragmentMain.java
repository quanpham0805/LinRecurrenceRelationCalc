package com.example.linrecurrencerelation;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

public class FragmentMain extends Fragment {

    public MainViewModel mainViewModel;
    final String LOG_TAG = FragmentMain.class.getSimpleName();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_main, container, false);

        view.findViewById(R.id.button_config).setOnClickListener(v ->
                Navigation.findNavController(view).navigate(R.id.action_FragmentMain_to_FragmentConfig));

        view.findViewById(R.id.button_terms).setOnClickListener(v ->
                Navigation.findNavController(view).navigate(R.id.action_FragmentMain_to_fragmentTerms));


        mainViewModel = new ViewModelProvider(getActivity()).get(MainViewModel.class);
        Button btn_add = (Button) view.findViewById(R.id.button_add);
        Button btn_subtract = (Button) view.findViewById(R.id.button_subtract);
        Button btn_reset = (Button) view.findViewById(R.id.button_reset);
        Button btn_calculate = (Button) view.findViewById(R.id.button_calc);
        TextView tv_counter = (TextView) view.findViewById(R.id.tv_terms);
        EditText tv_modulo = (EditText) view.findViewById(R.id.edit_modulo);
        EditText tv_result = (EditText) view.findViewById(R.id.edit_result);
        TextView tv_ans = (TextView) view.findViewById(R.id.tv_ans);

        tv_counter.setText(Integer.toString(mainViewModel.getCounter()));

        btn_add.setOnClickListener(v -> {
            mainViewModel.AddOne();
            tv_counter.setText(Integer.toString(mainViewModel.getCounter()));
        });

        btn_subtract.setOnClickListener(v -> {
            mainViewModel.SubtractOne();
            tv_counter.setText(Integer.toString(mainViewModel.getCounter()));
        });

        btn_reset.setOnClickListener(v -> {
            mainViewModel.reset();
            tv_counter.setText(Integer.toString(1));
            tv_modulo.setText("");
            tv_result.setText("");
            tv_ans.setText("Answer = ");
        });

        btn_calculate.setOnClickListener(v -> {
            calculate(tv_modulo, tv_result, view);
        });
        return view;
    }

    private void calculate(EditText modulo, EditText result, View v) {

        TextView textView = (TextView) v.findViewById(R.id.tv_ans);

        if (validNumber(modulo) && validNumber(result)) {
            long mModulo = Long.parseLong(modulo.getText().toString());
            long mResult = Long.parseLong(result.getText().toString());
            int termLen = mainViewModel.getCounter();

            if (mResult <= termLen) {
                textView.setText(Integer.toString(mainViewModel.getTerms((int) mResult - 1)));
            } else {
                long[][] z = Matrix.MatrixExponentiation(Matrix.createBaseMatrix(mainViewModel.extractCoeffData(termLen)), mResult - (long) termLen , mModulo);
//                for (int i = 0 ; i < 4 ; i ++) {
//                    for (int j = 0 ; j < 4 ; j ++)
//                        Log.e(LOG_TAG, Long.toString(z[i][j]));
//                }
                z = Matrix.MatrixMultiplication(z, Matrix.createVerticalMatrix(mainViewModel.extractTermsData(termLen)), mModulo);
                textView.setText(Long.toString(z[0][0]));
            }

        } else {
            textView.setText("Invalid input");
        }
    }

    private boolean validNumber(EditText editText) {
        return (!TextUtils.isEmpty(editText.getText().toString()))
                && isParsableInteger(editText.getText().toString());
    }

    private boolean isParsableInteger(String input) {
        try {
            long z = Long.parseLong(input);
            return z != 0;
        } catch (final NumberFormatException e) {
            return false;
        }
    }

}
