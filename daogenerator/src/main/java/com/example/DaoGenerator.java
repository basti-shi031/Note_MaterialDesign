package com.example;

import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.Schema;

public class DaoGenerator {
    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(1, "greendao.utils");

        addNote(schema);
        addOrder(schema);

        new de.greenrobot.daogenerator.DaoGenerator().generateAll(schema, "D:\\Software\\AndroidStudioWorkspace\\MDTest\\app\\src\\main\\java-gen");
    }

    //¼ÇÂ¼Ë³Ðò
    private static void addOrder(Schema schema) {
        Entity note = schema.addEntity("Seq");
        note.addIdProperty();
        note.addStringProperty("orderId");

    }

    private static void addNote(Schema schema) {
        Entity note = schema.addEntity("Note");
        note.addIdProperty();
        note.addStringProperty("title");
        note.addStringProperty("content");
        note.addIntProperty("backgroundColor");
        note.addBooleanProperty("isLike");
        note.addDateProperty("createDate");
        note.addDateProperty("notifyDate");
        note.addStringProperty("Tag");
        note.addIntProperty("type");

    }


}
