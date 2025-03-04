package com.example.educationapp.Activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.educationapp.databinding.ActivitySupportPageBinding

class Support_page : AppCompatActivity() {
    private lateinit var binding: ActivitySupportPageBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySupportPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.back.setOnClickListener { onBackPressed() }
        val page_type=intent.getStringExtra("page_type")
        setContent(page_type,binding)

    }

    private fun setContent(pageType: String?, binding: ActivitySupportPageBinding) {
        val webView=binding.content
        if(pageType=="about"){
            binding.supp.text="About Us"
            webView.loadDataWithBaseURL(null, aboutus, "text/html", "UTF-8", null)

        }
        else if(pageType=="tc"){
            binding.supp.text="Terms and Conditions"
            webView.loadDataWithBaseURL(null, tc, "text/html", "UTF-8", null)

        }
        else{
            binding.supp.text="Privacy Policy"
            webView.loadDataWithBaseURL(null, pp, "text/html", "UTF-8", null)


        }

    }
}

private val tc="""<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Life Learning Education - Terms and Privacy</title>
</head>
<body>
    <!-- Terms and Conditions Section -->
    <section>
        <h1>Terms and Conditions</h1>
        <p><strong>Founder - Monu Sharma</strong></p>
        <p>Welcome to Life Learning Education! These terms and conditions outline the rules and regulations for the use of our website and services, located at <a href="http://lifelearningeducation.in/">http://lifelearningeducation.in/</a>.</p>
        <p>By accessing this website, we assume you accept these terms and conditions. Do not continue to use Life Learning Education if you do not agree to take all of the terms and conditions stated on this page.</p>

        <h2>1. Definitions</h2>
        <p>"Company" refers to Life Learning Education.<br>
        "You" refers to the user or visitor of this website.<br>
        "Services" refers to all products, content, and features provided by the Company through this website.</p>

        <h2>2. Use of the Website</h2>
        <p>You must be at least 18 years old or have parental/guardian consent to use our services.<br>
        By using this website, you agree to provide accurate and complete information when required.<br>
        Any misuse of the website for illegal activities is strictly prohibited.</p>

        <h2>3. Intellectual Property Rights</h2>
        <p>Unless otherwise stated, Life Learning Education owns the intellectual property rights for all material on this website. All intellectual property rights are reserved. You may access this material for personal use, subject to the restrictions set in these terms and conditions.</p>
        <p>You must not:</p>
        <ul>
            <li>Republish, sell, rent, or sub-license material from this website.</li>
            <li>Reproduce, duplicate, or copy material from this website.</li>
            <li>Redistribute content from Life Learning Education without prior written permission.</li>
        </ul>

        <h2>4. Services and Payments</h2>
        <p>All services offered are subject to availability and may be withdrawn or modified without prior notice.<br>
        Payments must be made in full before access to certain courses, products, or features is granted.<br>
        Pricing details for services and products are clearly mentioned on the website and are subject to change.</p>

        <h2>5. User Accounts</h2>
        <p>To access some parts of the website, you may need to create an account. You are responsible for maintaining the confidentiality of your account details.<br>
        The Company reserves the right to terminate accounts or refuse service at our sole discretion if these terms are violated.</p>

        <h2>6. Cancellation and Termination</h2>
        <p>Life Learning Education reserves the right to cancel or terminate services or accounts for violations of these terms or other unlawful behavior.<br>
        In case of termination, users may lose access to any course, subscription, or service without a refund.</p>

        <h2>7. Limitation of Liability</h2>
        <p>Life Learning Education will not be held responsible for any damages resulting from the use or inability to use our services. This includes direct, indirect, incidental, or consequential damages.</p>

        <h2>8. External Links</h2>
        <p>Our website may contain links to external websites for reference purposes. Life Learning Education does not control or endorse the content on these external websites and is not responsible for any damages caused by their use.</p>

        <h2>9. Privacy Policy</h2>
        <p><strong>Founder - Monu Sharma</strong><br>
        Your use of the website is also governed by our Privacy Policy, which can be found on the website.</p>

        <h2>10. Changes to Terms and Conditions</h2>
        <p>Life Learning Education reserves the right to update or modify these terms at any time without prior notice. By continuing to use the website after such changes, you agree to the updated terms.</p>

        <h2>11. Governing Law</h2>
        <p>These terms and conditions are governed by the laws of India. Any disputes arising in relation to these terms shall be subject to the exclusive jurisdiction of the courts in Haryana, India.</p>

        <h2>12. Refund Policy</h2>
        <p>The refunded amount will be credited back to the original payment method within 3-4 working days. For any refund-related queries, please contact this number: 99913 29616.</p>

        <h2>13. Contact Us</h2>
        <p><strong>Founder - Monu Sharma</strong><br>
        If you have any questions about these terms and conditions, please contact us at:<br>
        Email: <a href="mailto:Support@lifelearningeducation.in">Support@lifelearningeducation.in</a></p>
    </section>

    <!-- Privacy Policy Section -->
    <section>
        <h1>Privacy Policy</h1>
        <p><strong>Founder - Monu Sharma</strong></p>
        <p>At Life Learning Education, accessible via <a href="http://lifelearningeducation.in/">http://lifelearningeducation.in/</a>, we are committed to safeguarding your privacy. This Privacy Policy explains how we collect, use, and protect your personal information. By using our website, you agree to the practices described below.</p>

        <h2>Information We Collect</h2>
        <p>We may collect the following types of personal information:</p>
        <ul>
            <li>Name, email address, phone number, and address</li>
            <li>Payment details (processed securely through third-party gateways)</li>
            <li>Usage data and cookies for improving user experience</li>
        </ul>

        <h2>How We Use Your Information</h2>
        <p>We use your personal information to:</p>
        <ul>
            <li>Provide and deliver our educational services</li>
            <li>Respond to your inquiries and support requests</li>
            <li>Process payments and issue invoices</li>
            <li>Improve website functionality and analyze usage trends</li>
        </ul>

        <h2>Sharing Your Information</h2>
        <p>We do not share your personal information with third parties, except as required by law or to provide our services (e.g., payment processors).</p>

        <h2>Data Security</h2>
        <p>We use appropriate technical measures to protect your personal data from unauthorized access, alteration, or disclosure.</p>

        <h2>Your Rights</h2>
        <p>You have the right to access, update, or delete your personal information. For any queries, contact us at <a href="mailto:support@lifelearningeducation.in">support@lifelearningeducation.in</a>.</p>
    </section>
</body>
</html>""".trimIndent()
private val aboutus="""<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>About Us - Life Learning Education</title>
</head>
<body>
    <section>
        <h1>About Us – Life Learning Education</h1>
        <p>Welcome to Life Learning Education, the premier destination for students who wish to conquer the world of mathematics! We are passionate about transforming the way students approach math and making it an exciting, accessible subject for learners of all levels. Our mission is to guide, mentor, and empower students to achieve their full academic potential with confidence and ease.</p>
        
        <p>At Life Learning Education, we believe that math is not just about formulas and equations—it’s about problem-solving, logical thinking, and building essential skills that will help you succeed in school, exams, and life. We specialize in offering tailored math programs for students from various educational backgrounds, whether you are preparing for school exams, board exams, or competitive tests.</p>
        
        <p>Our platform, <a href="http://lifelearningeducation.in">lifelearningeducation.in</a>, offers an array of engaging and easy-to-understand online resources, including video lessons, interactive quizzes, and downloadable study material. The goal is to make learning math enjoyable while ensuring students build a strong foundation in mathematical concepts.</p>

        <h2>Our Approach</h2>
        <p>We take a student-centered approach in everything we do. Each lesson is designed to cater to the unique learning style and pace of the student. Here’s how we stand out:</p>
        
        <h3>Experienced Educators</h3>
        <p>Our team consists of highly qualified and experienced educators with a passion for teaching mathematics. With years of experience in teaching both individual students and large groups, they bring a wealth of knowledge and a deep understanding of student challenges.</p>
        
        <h3>Comprehensive Learning Resources</h3>
        <p>Whether you need help with basic algebra, geometry, calculus, or preparation for competitive exams, our extensive library of lessons, worksheets, and practice tests will guide you through every step. We offer a holistic approach, covering both theoretical knowledge and practical applications.</p>
        
        <h3>Interactive & Engaging Lessons</h3>
        <p>We use state-of-the-art technology and multimedia tools to make learning interactive. Our engaging video lessons and interactive exercises encourage students to actively participate in their learning journey and make math fun and rewarding.</p>
        
        <h3>Personalized Guidance & Support</h3>
        <p>At Life Learning Education, we understand that every student is unique. Our personalized approach ensures that students receive the individual attention they need to overcome their challenges and develop a deep understanding of math. We offer one-on-one tutoring sessions to further reinforce learning.</p>
        
        <h3>Exam Preparation & Practice</h3>
        <p>We help students prepare for board exams, competitive exams, and school assessments by offering specialized programs tailored to each exam’s format and syllabus. Our practice tests and mock exams simulate real exam conditions, boosting your confidence and improving your problem-solving skills.</p>
        
        <h3>Affordable Learning</h3>
        <p>We believe in making quality education accessible to all. Our courses are designed to provide maximum value at affordable prices, ensuring that every student can benefit from our expert tutoring and resources.</p>

        <h2>Why Choose Life Learning Education?</h2>
        <ul>
            <li><strong>Expert Tutors:</strong> Our team of experienced teachers is committed to providing the best education and guidance to each student.</li>
            <li><strong>Comprehensive Curriculum:</strong> From basic to advanced levels, we cover all areas of mathematics and focus on strengthening the core concepts.</li>
            <li><strong>Engaging Content:</strong> Our interactive lessons and quizzes ensure that students stay engaged while learning at their own pace.</li>
            <li><strong>Flexible Learning:</strong> Learn at your convenience, from anywhere, with our online classes and materials available 24/7.</li>
            <li><strong>Focused Exam Prep:</strong> Specialized courses for exam preparation to help you succeed in school, boards, or competitive exams.</li>
            <li><strong>Affordable Pricing:</strong> Get access to top-notch education without breaking the bank.</li>
        </ul>

        <h2>Our Vision</h2>
        <p>Our vision is to be the leading math education platform in India, empowering students to develop a passion for mathematics and guiding them to success in their academic and professional lives. We aim to bridge the gap between students and quality education by providing expert math resources, one-on-one support, and personalized learning plans.</p>
        
        <p>Join us at Life Learning Education and take the first step toward mastering math with ease and confidence. Explore our resources and experience the difference in your learning journey!</p>
    </section>
</body>
</html>"""
private val pp="""<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Privacy Policy - Life Learning Education</title>
</head>
<body>
    <section>
        <h1>Privacy Policy</h1>
        <p><strong>Founder - Monu Sharma</strong></p>
        <p>At Life Learning Education, accessible via <a href="http://lifelearningeducation.in/">http://lifelearningeducation.in/</a>, we are committed to safeguarding your privacy. This Privacy Policy explains how we collect, use, and protect your personal information. By using our website, you agree to the practices described below.</p>

        <h2>Information We Collect</h2>
        <p>We may collect the following types of personal information:</p>
        <ul>
            <li>Name, email address, phone number, and address</li>
            <li>Payment details (processed securely through third-party gateways)</li>
            <li>Usage data and cookies for improving user experience</li>
        </ul>

        <h2>How We Use Your Information</h2>
        <p>We use your personal information to:</p>
        <ul>
            <li>Provide and deliver our educational services</li>
            <li>Respond to your inquiries and support requests</li>
            <li>Process payments and issue invoices</li>
            <li>Improve website functionality and analyze usage trends</li>
        </ul>

        <h2>Sharing Your Information</h2>
        <p>We do not share your personal information with third parties, except as required by law or to provide our services (e.g., payment processors).</p>

        <h2>Data Security</h2>
        <p>We use appropriate technical measures to protect your personal data from unauthorized access, alteration, or disclosure.</p>

        <h2>Your Rights</h2>
        <p>You have the right to access, update, or delete your personal information. For any queries, contact us at <a href="mailto:support@lifelearningeducation.in">support@lifelearningeducation.in</a>.</p>
    </section>
</body>
</html>"""