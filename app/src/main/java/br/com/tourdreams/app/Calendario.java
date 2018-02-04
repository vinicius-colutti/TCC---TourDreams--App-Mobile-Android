package br.com.tourdreams.app;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

/**
 * Created by 15251365 on 17/10/2017.
 */

public class Calendario extends DialogFragment
        implements DatePickerDialog.OnDateSetListener{

    TextView data;

    public Calendario(TextView data){
      this.data = data;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        //criação do calendário
        // MOTIVO DE USAR ESSA FUNÇÃO: na criação do Dialog seta a data atual do sistema

        final Calendar c = Calendar.getInstance();
        int ano = c.get(Calendar.YEAR);
        int mes = c.get(Calendar.MONTH);
        int dia = c.get(Calendar.DAY_OF_MONTH);

        // cria uma nova instancia de calendario e retorna
        return new DatePickerDialog(getActivity(),this,ano,mes,dia);

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month , int dayOfMonth) {
        //função executada apos a escolha da data
        String dataSelecionada = String.format("%d/%02d/%02d",year , ++month, dayOfMonth);
        data.setText(dataSelecionada);
    }



}
