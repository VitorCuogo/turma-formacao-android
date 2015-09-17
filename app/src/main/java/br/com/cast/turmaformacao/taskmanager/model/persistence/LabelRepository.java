package br.com.cast.turmaformacao.taskmanager.model.persistence;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import br.com.cast.turmaformacao.taskmanager.model.entities.Label;

public final class LabelRepository {

    private LabelRepository() {
        super();
    }

    public static void save(Label label) {
        DatabaseHelper labelDatabaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = labelDatabaseHelper.getWritableDatabase();

        ContentValues values = LabelContract.getContenteValues(label);
        if (label.getId() == null) {
            db.insert(LabelContract.TABLE, null, values);
        } else {
            String where = LabelContract.ID + " = ? ";
            String[] params = {label.getId().toString()};
            db.update(LabelContract.TABLE, values, where, params);
        }

        db.close();
        labelDatabaseHelper.close();
    }

    public static void delete(long id) {
        DatabaseHelper labelDatabaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = labelDatabaseHelper.getWritableDatabase();

        String where = LabelContract.ID + " = ? ";
        String[] params = {String.valueOf(id)};
        db.delete(LabelContract.TABLE, where, params);

        db.close();
        labelDatabaseHelper.close();
    }

    public static List<Label> getAll() {
        DatabaseHelper labelDatabaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = labelDatabaseHelper.getReadableDatabase();

        Cursor cursor = db.query(LabelContract.TABLE, LabelContract.COLUNS, null, null, null, null, LabelContract.ID);
        List<Label> values = LabelContract.getLabels(cursor);

        db.close();
        labelDatabaseHelper.close();
        return values;
    }
}
