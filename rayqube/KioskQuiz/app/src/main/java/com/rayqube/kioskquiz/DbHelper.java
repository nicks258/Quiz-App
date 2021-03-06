package com.rayqube.kioskquiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JoseThomas on 3/31/2016.
 */
public class DbHelper extends SQLiteOpenHelper {

    //DB version, table and database name
    private static final int DB_VERSION = 2;
    private static final String DB_NAME = "quizdb";
    private static final String DB_TABLE = "quiztable";
    private static final String DB_TABLE1 = "scoretable";
    //table column names
    private static final String KEY_ID = "id";
    private static final String KEY_QUES = "question";
    private static final String KEY_ANSWER = "answer";
    private static final String KEY_OPTA = "optA";
    private static final String KEY_OPTB = "optB";
    private static final String KEY_OPTC = "optC";
    private static final String KEY_OPTD = "optD";
    private static final String KEY_OPTE = "optE";
    private static final String KEY_NAME = "name";
    private static final String KEY_MAILID = "mailID";
    private static final String KEY_SCORE = "score";
    private static final String KEY_TIME = "time";
    private SQLiteDatabase dbase;
    private int rowCount = 0;

    public DbHelper(Context context){
        super(context,DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        dbase = db;
        String sqlQuery = String.format("CREATE TABLE IF NOT EXISTS %s ( %s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT UNIQUE , %s TEXT UNIQUE, %s TEXT UNIQUE, %s TEXT UNIQUE, %s TEXT UNIQUE, %s TEXT UNIQUE, %s TEXT )", DB_TABLE, KEY_ID, KEY_QUES, KEY_ANSWER, KEY_OPTA, KEY_OPTB, KEY_OPTC,KEY_OPTD,KEY_OPTE);
        Log.d("TaskDBHelper", "Query to form table" + sqlQuery);

        String sqlQuery1 = String.format("CREATE TABLE IF NOT EXISTS %s ( %s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s TEXT, %s TEXT , %s TEXT )", DB_TABLE1, KEY_ID, KEY_NAME, KEY_MAILID, KEY_SCORE,KEY_TIME);
        Log.d("TaskDBHelper", "Query to form table" + sqlQuery);
        db.execSQL(sqlQuery1);
        db.execSQL(sqlQuery);
//        addQuestions();
    }

//    private void addQuestions() {
//        Question q1 = new Question("Which company is the largest manufacturer of network equipment ?", "HP", "IBM", "CICSO", "CISCO");
//        this.addQuestionToDB(q1);
//        Question q2 = new Question("Which of the following is NOT an operating system ?", "Linux", "BIOS", "DOS", "BIOS");
//        this.addQuestionToDB(q2);
//        Question q3 = new Question("Who is the founder of Apple Inc. ?", "Jose Thomas", "Bill Gates", "Steve Jobs", "Steve Jobs");
//        this.addQuestionToDB(q3);
//        Question q4 = new Question("Who is the first cricketer to score an international double century in 50-over match ?", "Ricky Ponting", "Sachin Tendulkar", "Brian Lara", "Sachin Tendulkar");
//        this.addQuestionToDB(q4);
//        Question q5 = new Question("Which is the biggest largest city in the world ?", "Vienna", "Reno", "Delhi", "Reno");
//        this.addQuestionToDB(q5);
//        Question q6 = new Question("Which is the biggest desert in in the world ?", "Thar", "Sahara", "Mohave", "Sahara");
//        this.addQuestionToDB(q6);
//        Question q7 = new Question("Which is the the largest coffee growing country in the world ?", "Brazil", "India", "Myanmar", "Brazil");
//        this.addQuestionToDB(q7);
//        Question q8 = new Question("Which is the longest river in the world ?", "Ganga", "Amazon", "Nile", "Nile");
//        this.addQuestionToDB(q8);
//        Question q9 = new Question("Which country is known as the country of copper ?", "Somalia", "Cameroon", "Zambia", "Zambia");
//        this.addQuestionToDB(q9);
//        Question q10 = new Question("Which is the world's oldest known city ?", "Rome", "Damascus", "Jerusalem", "Damascus");
//        this.addQuestionToDB(q10);
//        Question q11 = new Question("Who is the first Prime minister of India ?", "Jawaharlal Nehru", "Dr.Rajendra Prasad", "Lal Bahadur Shasthri", "Jawaharlal Nehru");
//        this.addQuestionToDB(q11);
//        Question q12 = new Question("Which city is known as the city of canals ?", "Paris", "Venice", "London", "Venice");
//        this.addQuestionToDB(q12);
//        Question q13 = new Question("Australia was discovered by ?", "James Cook", "Columbus", "Magallan", "James Cook");
//        this.addQuestionToDB(q13);
//        Question q14 = new Question("The national flower of Britain is ?", "Lily", "Rose", "Lotus", "Rose");
//        this.addQuestionToDB(q14);
//        Question q15 = new Question("The red cross was founded by ?", "Gullivar Crossby", "Alexandra Maria Lara", "Jean Henri Durant", "Jean Henri Durant");
//        this.addQuestionToDB(q15);
//        Question q16 = new Question("Which place is known as the roof of the world ?", "Alphs", "Tibet", "Nepal", "Tibet");
//        this.addQuestionToDB(q16);
//        Question q17 = new Question("Who invented washing machine ?", "James King", "Alfred Vincor", "Christopher Marcossi", "James King");
//        this.addQuestionToDB(q17);
//        Question q18 = new Question("Who won the Football world Cup in 2014 ?", "Italy", "Argentina", "Germany", "Germany");
//        this.addQuestionToDB(q18);
//        Question q19 = new Question("Who won the Cricket World cup in 2011 ?", "Australia", "India", "England", "India");
//        this.addQuestionToDB(q19);
//        Question q20 = new Question("The number regarded as the lucky number in Italy is ?", "13", "7", "9", "13");
//        this.addQuestionToDB(q20);
//
//    }

    public void addQuestionToDB(Question q){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_QUES, q.getQuestion());
        values.put(KEY_ANSWER,q.getAnswer());
        values.put(KEY_OPTA,q.getOptA());
        values.put(KEY_OPTB,q.getOptB());
        values.put(KEY_OPTC,q.getOptC());
        values.put(KEY_OPTD,q.getOptD());
        values.put(KEY_OPTE,q.getOptE());
        db.insert(DB_TABLE, null, values);
        db.close();
    }

    public void addScoreDetail(People people){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, people.getName());
        values.put(KEY_MAILID,people.getMailId());
        values.put(KEY_SCORE,people.getScore());
        values.put(KEY_TIME,people.getTime());
        db.insert(DB_TABLE1, null, values);
        db.close();
    }

    public List<People> getPeopleScore(){
        List<People> peopleList =  new ArrayList<>();
        dbase = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM "+DB_TABLE1;
        Cursor cursor = dbase.rawQuery(selectQuery,null);
        rowCount = cursor.getCount();

        if(cursor.moveToFirst()){
            do{
                People q = new People();
                q.setName(cursor.getString(cursor.getColumnIndex(KEY_NAME)));
                q.setMailId(cursor.getString(cursor.getColumnIndex(KEY_MAILID)));
                q.setScore(cursor.getString(cursor.getColumnIndex(KEY_SCORE)));
                q.setTime(cursor.getString(cursor.getColumnIndex(KEY_TIME)));
                peopleList.add(q);

            }while (cursor.moveToNext());
        }
        return peopleList;
    }

    public List <Question> getAllQuestions(){
        List <Question> questionList = new ArrayList<Question>();

        dbase = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM "+DB_TABLE;
        Cursor cursor = dbase.rawQuery(selectQuery,null);
        rowCount = cursor.getCount();

        if(cursor.moveToFirst()){
            do{
                Question q = new Question();
                q.setId(cursor.getInt(0));
                q.setQuestion(cursor.getString(1));
                q.setAnswer(cursor.getString(2));
                q.setOptA(cursor.getString(3));
                q.setOptB(cursor.getString(4));
                q.setOptC(cursor.getString(5));
                q.setOptD(cursor.getString(6));
                q.setOptE(cursor.getString(7));
                questionList.add(q);

            }while (cursor.moveToNext());
        }
        return questionList;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+DB_TABLE);
        onCreate(db);
    }

    public int getRowCount(){
        return rowCount;
    }
}
