package fastcampus.part2.sharelocationapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.KakaoSdk
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import fastcampus.part2.sharelocationapp.databinding.ActivityLoginBinding

class LoginActivity: AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    private val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
        if (error != null){
            // 로그인 실패
        }else if(token != null){
            //로그인 성공
            Log.e("loginActivity", "login in with kakao account token: $token")
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        KakaoSdk.init(this,"6b2bac974648bb5227345068077793de")

        binding.btnKakaoLogin.setOnClickListener {
            if(UserApiClient.instance.isKakaoTalkLoginAvailable(this)){
                // 카카오톡 로그인
                UserApiClient.instance.loginWithKakaoTalk(this){token, error ->
                    if(error != null){
                        // 카카오톡 로그인 실패

                        if(error is ClientError &&error.reason == ClientErrorCause.Cancelled){
                            //유저가 취소한 경우
                            return@loginWithKakaoTalk
                        }
                        // 유저가 취소한 경우X
                        UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)
                        error.printStackTrace()
                    }else if(token != null){
                        // 로그인 성공
                        Log.e("loginActivity", "token == $token")
                    }

                }

            }
            else {
                // 카카오계정 로그인
                UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)

            }
        }




    }

}