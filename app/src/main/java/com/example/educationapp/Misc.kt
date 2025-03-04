package com.example.educationapp

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.widget.Toast
import com.example.educationapp.Activity.MainActivity
import com.example.educationapp.Activity.SignUpActivity

class Misc (val context: Context){
    fun toast(message:String){
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
    }

    fun startActivity(java: Class<SignUpActivity>) {

    }
    fun getid()=PreferenceHelper(context).getUserId()
    fun saverating(cid:String)=PreferenceHelper(context).saveratedcourse(cid)
    fun getrating()=PreferenceHelper(context).getRatedCourse()
    fun showLogoutDialog( onLogout: () -> Unit) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Logout")
        builder.setMessage("Do you want to logout?")

        // Set up the "Yes" button
        builder.setPositiveButton("Yes") { dialog: DialogInterface, _: Int ->
            PreferenceHelper(context).clearUserId() // Call the logout function
          context.startActivity(Intent(context, MainActivity::class.java))
            onLogout
            dialog.dismiss() // Dismiss the dialog
        }

        // Set up the "No" button
        builder.setNegativeButton("No") { dialog: DialogInterface, _: Int ->
            dialog.dismiss() // Dismiss the dialog without logging out
        }

        // Show the dialog
        builder.create().show()
    }

}



class PreferenceHelper(context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("UserPreferences", Context.MODE_PRIVATE)

    companion object {
        private const val USER_ID = "user_id"
        private const val RATED_COURSE="rated_course"
        private const val PHONE_NUMBER="phone_number"
    }

      fun saveratedcourse(cid:String){
         val intialset=getRatedCourse()
          intialset.add(cid)
          val editor = sharedPreferences.edit()
          editor.putStringSet(RATED_COURSE, intialset)
          editor.apply()
      }
    fun getRatedCourse(): MutableSet<String> {
        return sharedPreferences.getStringSet(RATED_COURSE, mutableSetOf()) ?: mutableSetOf()
    }

    // Save the user ID
    fun saveUserId(userId: Int) {
        val editor = sharedPreferences.edit()
        editor.putInt(USER_ID, userId)
        editor.apply()
    }

    fun savePhoneNumber(phoneNumber: String) {
        val editor = sharedPreferences.edit()
        editor.putString(PHONE_NUMBER,phoneNumber)
        editor.apply()
    }
    fun getPhoneNumber(): String? {
        return sharedPreferences.getString(PHONE_NUMBER, null)
    }


    // Retrieve the user ID
    fun getUserId(): Int? {
        return sharedPreferences.getInt(USER_ID, -1)
    }

    // Clear the user ID
    fun clearUserId() {
        val editor = sharedPreferences.edit()
        editor.remove(USER_ID)
        editor.remove(PHONE_NUMBER)
        editor.apply()
    }
}
