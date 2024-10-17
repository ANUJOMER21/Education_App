package com.example.educationapp.APi
import com.codebyashish.autoimageslider.Models.ImageSlidesModel
import kotlinx.coroutines.delay
class TestRepository : RepositoryImpl {
    override suspend fun login(username: String, password: String): Result<LoginResponse> {
        delay(5000)
        if(username.equals("1234567890")&&password.equals("test"))
          return Result.success(LoginResponse(success = "true", id = 1))
        else return  Result.failure(Throwable("Invalid Credentials"))
    }

    override suspend fun signup(username: String, password: String, phoneNumber: String, referralCode: String,email:String,secphone:String): Result<LoginResponse> {
        delay(5000)
        return Result.success(LoginResponse(success = "true", id = 1))
    }

    override suspend fun GetSliders(): Result<List<Slider>> {
        return  Result.success(listOf(
            Slider("https://img.freepik.com/free-psd/geek-pride-day-banner-theme_23-2148559505.jpg?w=1800&t=st=1724172032~exp=1724172632~hmac=78e00ad993163f42b41048ee79bb80a563138c55ceed5dfe4ab88e6567eb0e21",""),
            Slider("https://img.freepik.com/free-psd/geek-pride-day-banner-theme_23-2148559505.jpg?w=1800&t=st=1724172032~exp=1724172632~hmac=78e00ad993163f42b41048ee79bb80a563138c55ceed5dfe4ab88e6567eb0e21",""),
            Slider("https://img.freepik.com/free-psd/geek-pride-day-banner-theme_23-2148559505.jpg?w=1800&t=st=1724172032~exp=1724172632~hmac=78e00ad993163f42b41048ee79bb80a563138c55ceed5dfe4ab88e6567eb0e21","")))

    }

    override suspend fun GetFeatureCourse(): Result<ArrayList<Course>> {
        return  Result.success(ArrayList(
            listOf(
                Course(
                    "C1",
                    "$image",
                    "Introduction to Python",
                    "12 weeks",

                ),
                Course(
                    "C2",
                    "$image",
                    "Web Development with React",
                    "8 weeks",

                ),
                Course(
                    "C3",
                    "$image",
                    "Data Structures and Algorithms",
                    "16 weeks",

                ),
                Course(
                    "C4",
                    "$image",
                    "Machine Learning Basics",
                    "10 weeks",

                )
            )))
    }

    override suspend fun GetAllCourse(): Result<ArrayList<Course>> {
        return  Result.success(ArrayList(
            listOf(
                Course(
                    "C1",
                    "$image",
                    "Introduction to Python",
                    "12 weeks",

                ),
                Course(
                    "C2",
                    "$image",
                    "Web Development with React",
                    "8 weeks",

                ),
                Course(
                    "C3",
                    "$image",
                    "Data Structures and Algorithms",
                    "16 weeks",

                ),
                Course(
                    "C4",
                    "$image",
                    "Machine Learning Basics",
                    "10 weeks",

                ),
                        Course(
                        "C5",
                "$image",
                "Advanced Python Programming",
                "12 weeks",

            ),
            Course(
                "C6",
                "$image",
                "Full Stack Web Development",
                "24 weeks",

            ),
            Course(
                "C7",
                "$image",
                "Database Management Systems",
                "8 weeks",

            ),
            Course(
                "C8",
                "$image",
                "Mobile App Development (Android)",
                "12 weeks",

            ),
            Course(
                "C9",
                "$image",
                "DevOps and Cloud Computing",
                "10 weeks",

            ),
            Course(
                "C10",
                "$image",
                "Cybersecurity Fundamentals",
                "8 weeks",
            )
            )))
          }

    override suspend fun GetAllCategory(): Result<ArrayList<Category>> {
          return Result.success(
              ArrayList(
              listOf(
                  Category(cat,"Category1"),
                  Category(cat,"Category2"),
                  Category(cat,"Category3"),
                  Category(cat,"Category4"),
                  Category(cat,"Category5"),
                  Category(cat,"Category6"),
                  Category(cat,"Category7"),

          )
          )
          )
    }

    override suspend fun fetch_profile(phone: String): Result<ProfileDetails> {
        TODO("Not yet implemented")
    }

    override suspend fun getCourseCategoryWise(categoryId: String): Result<ArrayList<Course>> {
        TODO("Not yet implemented")
    }

    override suspend fun updateprofile(profile: UpdateProfileRequest): Result<success_response> {
        TODO("Not yet implemented")
    }

    override suspend fun discount(courseid: String, copon: String): Result<disc_success_response> {
        TODO("Not yet implemented")
    }

    override suspend fun Purchase(
        phone: String,
        courseid: String,
        price: String
    ): Result<success_response> {
        TODO("Not yet implemented")
    }

    override suspend fun my_course(phone: String): Result<MyCourse> {
        TODO("Not yet implemented")
    }

    override suspend fun my_course_content(course_id: String): Result<CourseContent> {
        TODO("Not yet implemented")
    }

    override suspend fun course_progress(
        user_id: String,
        course_id: String,
        video_id: String
    ): Result<course_progress> {
        TODO("Not yet implemented")
    }

    override suspend fun request_live_class(
        user_id: String,
        course_id: String
    ): Result<success_response> {
        TODO("Not yet implemented")
    }

    override suspend fun get_live_class_details(
        user_id: String,

    ): Result<get_live_class_details> {
        TODO("Not yet implemented")
    }

    override suspend fun Purchase_live_class(
        phone: String,
        live_id: String,
        price: String
    ): Result<success_response> {
        TODO("Not yet implemented")
    }

    override suspend fun changepass(
        user_id: String,
        newpass: String,
        oldpass: String
    ): Result<success_response> {
        TODO("Not yet implemented")
    }

    override suspend fun fetch_wallet(phone: String): Result<Fetch_Wallet> {
        TODO("Not yet implemented")
    }

    override suspend fun withdraw(phone: String, amount: String, upiid: String): Result<withdraw> {
        TODO("Not yet implemented")
    }

    override suspend fun Searchet(courseName: String): Result<ArrayList<Course>> {
        TODO("Not yet implemented")
    }

    override suspend fun is_course_purchased(
        phone: String,
        course_id: String
    ): Result<is_course_purchased> {
        TODO("Not yet implemented")
    }

    override suspend fun rate_course(
        user_id: String,
        course_id: String,
        rating: String
    ): Result<rate_res> {
        TODO("Not yet implemented")
    }


    val image="https://img.freepik.com/free-psd/geek-pride-day-banner-theme_23-2148559505.jpg?w=1800&t=st=1724172032~exp=1724172632~hmac=78e00ad993163f42b41048ee79bb80a563138c55ceed5dfe4ab88e6567eb0e21"
    val cat=image

}