package com.example.educationapp.APi

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codebyashish.autoimageslider.Enums.ImageScaleType
import com.codebyashish.autoimageslider.Models.ImageSlidesModel
import kotlinx.coroutines.launch

class ApiViewModel(private val repository: RepositoryImpl):ViewModel() {
    private val _loginState = MutableLiveData<LoginUiState>()
    val loginState: LiveData<LoginUiState> = _loginState

    private val _signupState = MutableLiveData<SignupUiState>()
    val signupState: LiveData<SignupUiState> = _signupState
    fun login(username: String, password: String) {
        _loginState.value = LoginUiState.Loading
        viewModelScope. launch {
            val result = repository.login(username, password)
            _loginState.value = result.fold(
                onSuccess = { LoginUiState.Success(it) },
                onFailure = { LoginUiState.Error(it.message ?: "Unknown Error") }
            )
        }
    }
    fun signup(username: String, password: String, phone: String,referal:String,email:String,secondphone:String) {
        _signupState.value = SignupUiState.Loading
        viewModelScope.launch {
            val result = repository.signup(username, password, phone,referal,email,secondphone)
            _signupState.value = result.fold(
                onSuccess = { SignupUiState.Success(it) },
                onFailure = { SignupUiState.Error(it.message ?: "Unknown Error") }
            )
        }
    }
    private val _sliderState = MutableLiveData<ArrayList<ImageSlidesModel>>()
    val sliderState: LiveData<ArrayList<ImageSlidesModel>> = _sliderState
    fun getSlider(){
        viewModelScope.launch {
            val result=repository.GetSliders()

            result.fold(
                onSuccess = {
                    val list=ArrayList<ImageSlidesModel>()
                    it.forEach {
                        Log.d("IMAGEURL",it.imageUrl.toString())
                        list.add(ImageSlidesModel(it.imageUrl,ImageScaleType.FIT))
                    }
                    _sliderState.value=list
                },
                onFailure = {
                    Log.d("result",it.toString())
                    _sliderState.value=ArrayList()
                }
            )
        }
    }
    private val _featureCourseState = MutableLiveData<ArrayList<Course>>()
    val featureCourseState: LiveData<ArrayList<Course>> = _featureCourseState
    fun getFeatureCourse(){
        viewModelScope.launch {
            val result=repository.GetFeatureCourse()
            result.fold(
                onSuccess = {
                    _featureCourseState.value=it.toCollection(ArrayList())
                },
                onFailure = {
                    _featureCourseState.value=ArrayList()
                }
            )
        }
    }
    private val _allCourseState = MutableLiveData<ArrayList<Course>>()
    val allCourseState: LiveData<ArrayList<Course>> = _allCourseState
    fun getAllCourse(){
        viewModelScope.launch {
            val result=repository.GetAllCourse()
            result.fold(
                onSuccess = {
                    _allCourseState.value=it.toCollection(ArrayList())
                },
                onFailure = {
                    _allCourseState.value=ArrayList()
                }
            )
        }
    }
    private val _allCategoryState=MutableLiveData<ArrayList<Category>>()
    val allCategoryState:LiveData<ArrayList<Category>> = _allCategoryState
    fun getAllCategory(){
        viewModelScope.launch {
            val  result=repository.GetAllCategory()
            result.fold(
                onSuccess = {
                    _allCategoryState.value=it.toCollection(ArrayList())
                },
                onFailure = {
                    _allCategoryState.value = ArrayList()
                }
            )
        }
    }

    private val _profileState = MutableLiveData<ProfileDetails?>()
    val profileState: LiveData<ProfileDetails?> = _profileState

    private val _courseCategoryState = MutableLiveData<ArrayList<Course>?>()
    val courseCategoryState: LiveData<ArrayList<Course>?> = _courseCategoryState

    private val _updateProfileState = MutableLiveData<success_response?>()
    val updateProfileState: LiveData<success_response?> = _updateProfileState
    private val _changepass = MutableLiveData<success_response?>()
    val changepass: LiveData<success_response?> = _changepass

    private val _discountState = MutableLiveData<disc_success_response?>()
    val discountState: LiveData<disc_success_response?> = _discountState

    private val _purchaseState = MutableLiveData<success_repponse_purchase?>()
    val purchaseState: LiveData<success_repponse_purchase?> = _purchaseState

    private val _myCourseState = MutableLiveData<MyCourse?>()
    val myCourseState: LiveData<MyCourse?> = _myCourseState

    private val _courseContentState = MutableLiveData<CourseContent?>()
    val courseContentState: LiveData<CourseContent?> = _courseContentState

    private val _courseProgressState = MutableLiveData<course_progress?>()
    val courseProgressState: LiveData<course_progress?> = _courseProgressState

    private val _liveClassRequestState = MutableLiveData<success_response_Liveclass?>()
    val liveClassRequestState: LiveData<success_response_Liveclass?> = _liveClassRequestState
    private val _helpRequestState = MutableLiveData<help_supp?>()
    val helpRequestState: LiveData<help_supp?> = _helpRequestState

    private val _liveClassDetailsState = MutableLiveData<get_live_class_details?>()
    val liveClassDetailsState: LiveData<get_live_class_details?> = _liveClassDetailsState

    fun fetchProfile(phone: String) {
        viewModelScope.launch {
            val result = repository.fetch_profile(phone)
            result.fold(
                onSuccess = {
                    _profileState.value = it
                },
                onFailure = {
                    Log.d("Failure",it.toString())
                    _profileState.value = null
                }
            )
        }
    }

    fun getCourseCategoryWise(categoryId: String) {
        viewModelScope.launch {
            val result = repository.getCourseCategoryWise(categoryId)
            result.fold(
                onSuccess = {
                    _courseCategoryState.value = it
                },
                onFailure = {
                    _courseCategoryState.value = null
                }
            )
        }
    }

    fun updateProfile(profile: UpdateProfileRequest) {
        viewModelScope.launch {
            val result = repository.updateprofile(profile)
            result.fold(
                onSuccess = {
                    _updateProfileState.value = it
                },
                onFailure = {
                    _updateProfileState.value = null
                }
            )
        }
    }

    fun applyDiscount(courseId: String, coupon: String,userId: String) {
        viewModelScope.launch {
            val result = repository.discount(courseId, coupon,userId)
            result.fold(
                onSuccess = {
                    _discountState.value = it
                },
                onFailure = {
                    Log.d("failure discount",it.toString())
                    _discountState.value = null
                }
            )
        }
    }

    fun purchaseCourse(phone: String, courseId: String, price: String) {
        viewModelScope.launch {
            val result = repository.Purchase(phone, courseId, price)
            result.fold(
                onSuccess = {
                    _purchaseState.value = it
                },
                onFailure = {
                    Log.d("failure",it.toString())
                    _purchaseState.value = null
                }
            )
        }
    }

    fun fetchMyCourse(phone: String) {
        viewModelScope.launch {
            val result = repository.my_course(phone)
            result.fold(
                onSuccess = {
                    _myCourseState.value = it
                },
                onFailure = {
                    _myCourseState.value = null
                }
            )
        }
    }

    fun fetchCourseContent(courseId: String) {
        viewModelScope.launch {
            val result = repository.my_course_content(courseId)
            result.fold(
                onSuccess = {
                    _courseContentState.value = it
                },
                onFailure = {
                    Log.d("fetchCourseContent",it.toString())
                    _courseContentState.value = null
                }
            )
        }
    }

    fun trackCourseProgress(userId: String, courseId: String, videoId: String) {
        viewModelScope.launch {
            val result = repository.course_progress(userId, courseId, videoId)
            result.fold(
                onSuccess = {
                    _courseProgressState.value = it
                },
                onFailure = {
                    _courseProgressState.value = null
                }
            )
        }
    }

    fun requestLiveClass(userId: String, courseId: String) {
        viewModelScope.launch {
            val result = repository.request_live_class(userId, courseId)
            result.fold(
                onSuccess = {
                    _liveClassRequestState.value = it
                },
                onFailure = {
                    _liveClassRequestState.value = null
                }
            )
        }
    }

    fun fetchLiveClassDetails(courseId: String,userid: String) {
        viewModelScope.launch {
            val result = repository.get_live_class_details(courseId,userid)
            result.fold(
                onSuccess = {
                    _liveClassDetailsState.value = it
                },
                onFailure = {
                    Log.d("fetchLiveClassDetails",it.toString())
                    _liveClassDetailsState.value = null
                }
            )
        }
    }
    fun myLive_class(phone:String){
        viewModelScope.launch {
            val result=repository.myliveclass(phone)
            result.fold(
                onSuccess = {
                    _liveClassDetailsState.value=it
                },
                onFailure = {
                    Log.d("fetchLiveClassDetails",it.toString())
                    _liveClassDetailsState.value = null
                }
            )
        }
    }

    fun purchaseLiveClass(phone: String, liveId: String, price: String) {
        viewModelScope.launch {
            val result = repository.Purchase_live_class(phone, liveId, price)
            result.fold(
                onSuccess = {
                    _liveClassRequestState.value = it
                },
                onFailure = {
                    _liveClassRequestState.value = null
                }
            )
        }
    }
    fun changepassword(uid:String,newpass:String,oldpass:String){
     viewModelScope.launch {
         val result=repository.changepass(uid,newpass,oldpass)
         result.fold(
             onSuccess = {_changepass.value=it},
             onFailure = {_changepass.value=null}
         )
     }
    }
    private val _walletState = MutableLiveData<Fetch_Wallet?>()
    val walletState: LiveData<Fetch_Wallet?> = _walletState
    fun fetchwallet(phone: String){
        viewModelScope.launch {

            val result=repository.fetch_wallet(phone)
            result.fold(
                onSuccess = {_walletState.value=it},
                onFailure = {_walletState.value=null}

            )
        }
    }

      private val _withdrawState = MutableLiveData<withdraw?>()
    val withdrawState: LiveData<withdraw?> = _withdrawState
    fun withdraw(phone: String,amount:String,upiid:String){
        viewModelScope.launch {
            val result=repository.withdraw(phone,amount,upiid)
            result.fold(
                onSuccess = {_withdrawState.value=it},
                onFailure = {_withdrawState.value=null}
            )
        }
    }
    private val _searchState = MutableLiveData<ArrayList<Course>?>()
    val searchState: LiveData<ArrayList<Course>?> = _searchState
    fun search(courseName: String){
        viewModelScope.launch {
            val result=repository.Searchet(courseName)
            result.fold(
                onSuccess = {_searchState.value=it},
                onFailure = {_searchState.value=null}
            )
    }
        }
    private val _iscoursepur=MutableLiveData<is_course_purchased?>()
    val iscoursepur:LiveData<is_course_purchased?> = _iscoursepur
    fun iscoursepurchased(phone: String,courseid:String){
        viewModelScope.launch {
            val result = repository.is_course_purchased(phone, courseid)
            result.fold(
                onSuccess = { _iscoursepur.value = it },
                onFailure = { _iscoursepur.value = null }
            )
        }
    }
    private val _rateCourseState = MutableLiveData<rate_res?>()
    val rateCourseState: LiveData<rate_res?> = _rateCourseState
    fun ratecourse(user_id: String,course_id: String,rating: String){
        Log.d("rating","$user_id $course_id $rating")
        viewModelScope.launch {
            val result=repository.rate_course(user_id,course_id,rating)
            result.fold(
                onSuccess = {_rateCourseState.value=it},
                onFailure = {
                    Log.d("message",it.toString())
                    _rateCourseState.value=null}
            )

        }
    }
    private val _liveclassCourseState = MutableLiveData<ArrayList<Course>>()
    val liveclassCourseState: LiveData<ArrayList<Course>> = _liveclassCourseState

    fun Liveclass_details(user_id: String) {
        viewModelScope.launch {
            getAllCourse() // Ensure this fetches `allCourseState` correctly.

            // Observing `allCourseState` once and collecting the result
            allCourseState.observeForever { courses ->
                if (courses != null) {
                    val newCourse = ArrayList<Course>()

                    // Use `withContext` for repository calls (to ensure the correct dispatcher)
                    viewModelScope.launch {
                        courses.forEach { course ->
                            val cid = course.courseId
                            if (cid != null) {
                                val result = repository.get_live_class_details(cid, user_id)
                                result.fold(
                                    onSuccess = { data ->
                                        course.live_classs_no = data.data.size
                                        if (course.live_classs_no!! > 0) {
                                            newCourse.add(course)
                                        }
                                        Log.d("result", data.toString())
                                    },
                                    onFailure = { exception ->
                                        Log.d("message", exception.toString())
                                    }
                                )
                            }
                        }
                        // Update LiveData after processing all courses
                        _liveclassCourseState.postValue(newCourse)
                    }
                }
            }
        }
    }


    fun helpandsupport(user_id: String,message:String){
        viewModelScope.launch {
            val result=repository.helpsupport(user_id,message)
            result.fold(
                onSuccess = {_helpRequestState.value=it},
                onFailure = {it->
                    Log.d("HELPANDSUPPORT",it.toString())
                    _helpRequestState.value=null}
            )
        }
    }



}

sealed class LoginUiState {
    object Loading : LoginUiState()
    data class Success(val response: LoginResponse) : LoginUiState()
    data class Error(val message: String) : LoginUiState()
}


sealed class SignupUiState {
    object Loading : SignupUiState()
    data class Success(val response: LoginResponse) : SignupUiState()
    data class Error(val message: String) : SignupUiState()
}