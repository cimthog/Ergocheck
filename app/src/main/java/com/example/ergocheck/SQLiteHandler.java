/**
 * Author: Ravi Tamada
 * URL: www.androidhive.info
 * twitter: http://twitter.com/ravitamada
 * */
package com.example.ergocheck;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SQLiteHandler extends SQLiteOpenHelper {

	private static final String TAG = SQLiteHandler.class.getSimpleName();


	private static final int DATABASE_VERSION = 1;

	private static final String DATABASE_NAME = "Sales_Manager";

	private static final String TABLE_USER = "user";
	private static final String TABLE_MAIN_PRODUCT_DETAILS = "mainProductDetail";
	private static final String TABLE_SALES = "sales";

	// Login Table Columns names
	private static final String KEY_ID = "id";
	private static final String KEY_NAME = "name";
	private static final String KEY_EMAIL = "email";
	private static final String KEY_PHONENUMBER = "phoneNumber";
	private static final String KEY_REGION = "region";
	private static final String KEY_OUTLET = "outlet";
	private static final String KEY_PASSWORD = "password";


	private static final String KEY_MODEL = "model";
	private static final String KEY_BRAND = "brand";
	private static final String KEY_PRICE = "price";
	private static final String KEY_NUMBER_AVAILABLE = "number_available";
	private static final String KEY_PICTURE = "picture";

	private static final String KEY_TIME = "time";
	private static final String KEY_DATE = "date";
	private static final String KEY_DATE_LONG = "date_long";


//
//	private static final String KEY_NAME = "name";
//	private static final String KEY_EMAIL = "email";
//	private static final String KEY_USER_ID = "userId";
//	private static final String KEY_PHONENUMBER = "phoneNumber";
//	private static final String KEY_CREATED_AT = "created_at";
//	private static final String KEY_DEPARTMENT = "department";
//	private static final String KEY_INSTITUTION = "institution";
//	private static final String KEY_ABOUT = "about";

	public SQLiteHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// Creating Tables
	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_LOGIN_TABLE = "CREATE TABLE " + TABLE_USER + "("
				+ KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
				+ KEY_EMAIL + " TEXT UNIQUE,"+ KEY_PHONENUMBER + " TEXT," + KEY_REGION + " TEXT,"
				+ KEY_OUTLET + " TEXT," +  KEY_PASSWORD + " TEXT"+")";
		db.execSQL(CREATE_LOGIN_TABLE);



		String CREATE_MAIN_TABLE = "CREATE TABLE " + TABLE_MAIN_PRODUCT_DETAILS + "("
				+ KEY_ID + " INTEGER PRIMARY KEY," + KEY_MODEL + " TEXT UNIQUE,"
				+ KEY_BRAND + " TEXT,"+ KEY_PRICE + " TEXT," + KEY_NUMBER_AVAILABLE + " TEXT,"
				+ KEY_PICTURE + " BLOB" +")";

		db.execSQL(CREATE_MAIN_TABLE);


		String CREATE_SALES_TABLE= "CREATE TABLE " + TABLE_SALES + "("
				+ KEY_ID + " INTEGER PRIMARY KEY," + KEY_MODEL + " TEXT," + KEY_PRICE + " TEXT,"
				+ KEY_TIME + " TEXT ,"+ KEY_DATE + " TEXT,"  + KEY_DATE_LONG + " LONG," + KEY_PICTURE + " BLOB" +")";



		db.execSQL(CREATE_SALES_TABLE);



		Log.d(TAG, "Database tables created");
	}

	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);

		// Create tables again
		onCreate(db);
	}

	/**
	 * Storing user details in database
	 * */
	public void addUser(String name, String email, String phoneNumber, String password, String region, String outlet) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_NAME, name); // Name
		values.put(KEY_EMAIL, email);
		values.put(KEY_PHONENUMBER, phoneNumber);// Email
		values.put(KEY_REGION, region); // Email
		values.put(KEY_OUTLET, outlet); // Created At
		values.put(KEY_PASSWORD, password);


		// Inserting Row
		long id = db.insert(TABLE_USER, null, values);
		db.close(); // Closing database connection

		Log.d(TAG, "New user inserted into sqlite: " + id);
	}

	public void addMainProductDetails(String model, String brand, String price, String number_available, byte[]  productPicture) {//
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_MODEL, model);
		values.put(KEY_BRAND, brand);
		values.put(KEY_PRICE, price);
		values.put(KEY_NUMBER_AVAILABLE, number_available);
		values.put(KEY_PICTURE, productPicture);

		// Inserting Row
		long id = db.insert(TABLE_MAIN_PRODUCT_DETAILS, null, values);
		db.close();

		Log.d(TAG, "New main inserted into sqlite: " + id);
	}

	public void addSales(String model, String price, String time, String date, long dateLong , byte[] productPic) {//
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_MODEL, model); // Name
		values.put(KEY_PRICE, price);
		values.put(KEY_TIME, time);
		values.put(KEY_DATE, date);
		values.put(KEY_DATE_LONG, dateLong);
		values.put(KEY_PICTURE, productPic);


		// Inserting Row
		long id = db.insert(TABLE_SALES, null, values);
		db.close(); // Closing database connection

		Log.d(TAG, "New sale inserted into sqlite: " + id);
	}

	/**
	 * Getting user data from database
	 * */
	//1

	public HashMap<String, String> getUserDetails() {
		HashMap<String, String> user = new HashMap<String, String>();
		String selectQuery = "SELECT  * FROM " + TABLE_USER;

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		// Move to first row
		cursor.moveToFirst();
		if (cursor.getCount() > 0) {
			user.put("name", cursor.getString(1));
			user.put("email", cursor.getString(2));
			user.put("phoneNumber", cursor.getString(3));
			user.put("region", cursor.getString(4));
			user.put("outlet", cursor.getString(5));
			user.put("password", cursor.getString(6));


		}
		cursor.close();
		db.close();
		// return user
		Log.d(TAG, "Fetching user from Sqlite: " + user.toString());

		return user;
	}


	public HashMap<String, String> getMainProductDetails() {
		HashMap<String, String> ProductDetails = new HashMap<String, String>();
		String selectQuery = "SELECT  * FROM " + TABLE_MAIN_PRODUCT_DETAILS;

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		// Move to first row
		cursor.moveToFirst();
		if (cursor.getCount() > 0) {
			ProductDetails.put("model", cursor.getString(1));
			ProductDetails.put("brand", cursor.getString(2));
			ProductDetails.put("price", cursor.getString(3));
			ProductDetails.put("number_available", cursor.getString(4));
			//ProductDetails.put("picture", cursor.getString(5));



		}
		cursor.close();
		db.close();
		Log.d(TAG, "Fetching user product Details: " + ProductDetails.toString());

		return ProductDetails;
	}


	public String[] getMainProductModels() {
		String selectQuery = "SELECT  "+KEY_MODEL+" FROM " + TABLE_MAIN_PRODUCT_DETAILS;

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		String[] productModels= new String[cursor.getCount()];

		if (cursor.moveToFirst())
		{
			for (int i = 0; i < cursor.getCount(); i++)
			{
				productModels[i] = cursor.getString(0);
				cursor.moveToNext();
			}
		}
		cursor.close();
		db.close();
		Log.d(TAG, "Fetching user from products  models: " + productModels.toString());

		return productModels;
	}

	public String[] getMainProductBrand() {
		//	HashMap<String, String> ProductDetails = new HashMap<String, String>();
		String selectQuery = "SELECT  "+KEY_BRAND+" FROM " + TABLE_MAIN_PRODUCT_DETAILS;

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		String[] productBrand= new String[cursor.getCount()];

		if (cursor.moveToFirst())
		{
			for (int i = 0; i < cursor.getCount(); i++)
			{
				productBrand[i] = cursor.getString(0);
				cursor.moveToNext();
			}
		}
		cursor.close();
		db.close();
		// return user
		//Log.d(TAG, "Fetching user from Sqlite: " + ProductDetails.toString());

		return productBrand;
	}

	public List<byte[]> getProductImage() {
		String selectQuery = "SELECT  "+KEY_PICTURE+" FROM " + TABLE_MAIN_PRODUCT_DETAILS;

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		List<byte[]> productPic = new ArrayList<>();
		if (cursor.moveToFirst())
		{
			for (int i = 0; i < cursor.getCount(); i++)
			{
				productPic.add(cursor.getBlob(0));
				cursor.moveToNext();
			}
		}
		cursor.close();
		db.close();
		// return user
		//Log.d(TAG, "Fetching user from Sqlite: " + ProductDetails.toString());

		return productPic;
	}

	public List<byte[]> getProductImageFromSales() {
		//	HashMap<String, String> ProductDetails = new HashMap<String, String>();
		String selectQuery = "SELECT  "+KEY_PICTURE+" FROM " + TABLE_SALES;

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		List<byte[]> productPic = new ArrayList<>();
		if (cursor.moveToFirst())
		{
			for (int i = 0; i < cursor.getCount(); i++)
			{
				productPic.add(cursor.getBlob(0));
				cursor.moveToNext();
			}
		}
		cursor.close();
		db.close();
		// return user
		//Log.d(TAG, "Fetching user from Sqlite: " + ProductDetails.toString());

		return productPic;
	}




	public String[] getMainProductPrice() {
		//	HashMap<String, String> ProductDetails = new HashMap<String, String>();
		String selectQuery = "SELECT  "+KEY_PRICE+" FROM " + TABLE_MAIN_PRODUCT_DETAILS;

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		String[] productPrice= new String[cursor.getCount()];

		if (cursor.moveToFirst())
		{
			for (int i = 0; i < cursor.getCount(); i++)
			{
				productPrice[i] = cursor.getString(0);
				cursor.moveToNext();
			}
		}
		cursor.close();
		db.close();
		// return user
		//Log.d(TAG, "Fetching user from Sqlite: " + ProductDetails.toString());

		return productPrice;
	}


	public String[] getMainProductNumberAvailable() {
		//	HashMap<String, String> ProductDetails = new HashMap<String, String>();
		String selectQuery = "SELECT  "+KEY_NUMBER_AVAILABLE+" FROM " + TABLE_MAIN_PRODUCT_DETAILS;

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		String[] productNumberAvailable= new String[cursor.getCount()];

		if (cursor.moveToFirst())
		{
			for (int i = 0; i < cursor.getCount(); i++)
			{
				productNumberAvailable[i] = cursor.getString(0);
				cursor.moveToNext();
			}
		}
		cursor.close();
		db.close();
		// return user
		//Log.d(TAG, "Fetching user from Sqlite: " + ProductDetails.toString());

		return productNumberAvailable;
	}


//
//	public Byte[] getMainProductImages() {
//		//	HashMap<String, String> ProductDetails = new HashMap<String, String>();
//		String selectQuery = "SELECT  "+KEY_PICTURE+" FROM " + TABLE_MAIN_PRODUCT_DETAILS;
//
//		SQLiteDatabase db = this.getReadableDatabase();
//		Cursor cursor = db.rawQuery(selectQuery, null);
//
//		Byte [] productNumberAvailable= new Byte[][cursor.getCount()];
//
//		if (cursor.moveToFirst())
//		{
//			for (int i = 0; i < cursor.getCount(); i++)
//			{
//				productNumberAvailable[i] = cursor.getString(i);
//				cursor.moveToNext();
//			}
//		}
//		cursor.close();
//		db.close();
//		// return user
//		//Log.d(TAG, "Fetching user from Sqlite: " + ProductDetails.toString());
//
//		return productNumberAvailable;
//	}

	public void ChangePrice(String modelString, String price) {
		SQLiteDatabase db = this.getReadableDatabase();

//		String selectQuery = "SELECT "+KEY_PRICE+"  FROM  " + TABLE_MAIN_PRODUCT_DETAILS ;
//		Cursor cursor = db.rawQuery(selectQuery, null);
//		int RowID=0;
//		if (cursor.moveToFirst()) {
//			priceString=  cursor.getString(0);
//
//		}
//		  Double price = Double.parseDouble(String.valueOf(priceString));
//
//		Integer newNumberAvailable = numberAvailable-1;
//
//		cursor.close();
//		db.close();


		//Your Update to SQLite
		db = this.getReadableDatabase();
		ContentValues values = new ContentValues();
		values.put(KEY_PRICE, price );

		db.update(TABLE_MAIN_PRODUCT_DETAILS, values, KEY_MODEL + "=?", new String[]{modelString});
		db.close();

		Log.d(TAG, "Changed price succesfull ");


	}

	public void AddToNumberAvailable(String modelString, String newNumberToAdd) {

		String numberAvailableString="";
		SQLiteDatabase db = this.getReadableDatabase();
		String selectQuery = "SELECT "+KEY_NUMBER_AVAILABLE+"  FROM  " + TABLE_MAIN_PRODUCT_DETAILS +" WHERE "+KEY_MODEL+"='"+modelString+"'";;
		Cursor cursor = db.rawQuery(selectQuery, null);
		int RowID=0;
		if (cursor.moveToFirst()) {
			numberAvailableString=  cursor.getString(0);

		}
		Integer numberAvailable = Integer.parseInt(String.valueOf(numberAvailableString));

		Integer newNumberAvailable = numberAvailable+ Integer.parseInt(String.valueOf(newNumberToAdd));

		cursor.close();
		db.close();

		db = this.getReadableDatabase();
		ContentValues values = new ContentValues();
		values.put(KEY_NUMBER_AVAILABLE, String.valueOf(newNumberAvailable) );

		db.update(TABLE_MAIN_PRODUCT_DETAILS, values, KEY_MODEL + "=?", new String[]{modelString});
		db.close();

		Log.d(TAG, "Changed price succesfull ");


	}

	public void MinusOneFromDetailsNumberAvailable(String modelString) {
		String numberAvailableString="";
		SQLiteDatabase db = this.getReadableDatabase();
		String selectQuery = "SELECT "+KEY_NUMBER_AVAILABLE+"  FROM  " + TABLE_MAIN_PRODUCT_DETAILS +" WHERE "+KEY_MODEL+"='"+modelString+"'";
		Cursor cursor = db.rawQuery(selectQuery, null);
		int RowID=0;
		if (cursor.moveToFirst()) {
			numberAvailableString=  cursor.getString(0);

		}
		Integer numberAvailable = Integer.parseInt(String.valueOf(numberAvailableString));

		Integer newNumberAvailable = numberAvailable-1;

		cursor.close();
		db.close();


		//Your Update to SQLite
		db = this.getReadableDatabase();
		ContentValues values = new ContentValues();
		values.put(KEY_NUMBER_AVAILABLE, String.valueOf(newNumberAvailable));

		db.update(TABLE_MAIN_PRODUCT_DETAILS, values, KEY_MODEL + "='"+modelString+"'", null);
		db.close();

		Log.d(TAG, "minus one succesfull ");


	}

	public String[] getModelFromSales() {
		//	HashMap<String, String> ProductDetails = new HashMap<String, String>();
		String selectQuery = "SELECT  "+KEY_MODEL+" FROM " + TABLE_SALES;

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		String[] productModel= new String[cursor.getCount()];

		if (cursor.moveToFirst())
		{
			for (int i = 0; i < cursor.getCount(); i++)
			{
				productModel[i] = cursor.getString(0);
				cursor.moveToNext();
			}
		}
		cursor.close();
		db.close();
		// return user
		//Log.d(TAG, "Fetching user from Sqlite: " + ProductDetails.toString());

		return productModel;
	}


	public String[] getPriceFromSales() {
		//	HashMap<String, String> ProductDetails = new HashMap<String, String>();
		String selectQuery = "SELECT  "+KEY_PRICE+" FROM " + TABLE_SALES;

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		String[] salesPrice= new String[cursor.getCount()];

		if (cursor.moveToFirst())
		{
			for (int i = 0; i < cursor.getCount(); i++)
			{
				salesPrice[i] = cursor.getString(0);
				cursor.moveToNext();
			}
		}
		cursor.close();
		db.close();
		// return user
		//Log.d(TAG, "Fetching user from Sqlite: " + ProductDetails.toString());

		return salesPrice;
	}

	public String[] getTimeFromSales() {
		//	HashMap<String, String> ProductDetails = new HashMap<String, String>();
		String selectQuery = "SELECT  "+KEY_TIME+" FROM " + TABLE_SALES;

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		String[] time= new String[cursor.getCount()];

		if (cursor.moveToFirst())
		{
			for (int i = 0; i < cursor.getCount(); i++)
			{
				time[i] = cursor.getString(0);
				cursor.moveToNext();
			}
		}
		cursor.close();
		db.close();
		// return user
		//Log.d(TAG, "Fetching user from Sqlite: " + ProductDetails.toString());

		return time;
	}


	public String[] getDateFromSales() {
		//	HashMap<String, String> ProductDetails = new HashMap<String, String>();
		String selectQuery = "SELECT  "+KEY_DATE+" FROM " + TABLE_SALES;

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		String[] date= new String[cursor.getCount()];

		if (cursor.moveToFirst())
		{
			for (int i = 0; i < cursor.getCount(); i++)
			{
				date[i] = cursor.getString(0);
				cursor.moveToNext();
			}
		}
		cursor.close();
		db.close();
		// return user
		//Log.d(TAG, "Fetching user from Sqlite: " + ProductDetails.toString());

		return date;
	}


	/**
	 * Re crate database Delete all tables and create them again
	 * */
	public void deleteUsers() {
		SQLiteDatabase db = this.getWritableDatabase();
		// Delete All Rows
		db.delete(TABLE_USER, null, null);
		db.close();

		Log.d(TAG, "Deleted all user info from sqlite");
	}


}
