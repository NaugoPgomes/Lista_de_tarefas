package com.naugo.listadetarefas.verifica

import android.content.Context
import android.hardware.biometrics.BiometricManager
import android.os.Build
import androidx.core.content.ContextCompat

class VerificaLeitorDeDigital
{
    companion object {
        fun PodeLogarComDigital(context: Context): Boolean {
            if (Build.VERSION.SDK_INT == Build.VERSION_CODES.M) {
                return false
            }

            val biometricManager: androidx.biometric.BiometricManager = androidx.biometric.BiometricManager.from(context)
            when (biometricManager.canAuthenticate()) {
                androidx.biometric.BiometricManager.BIOMETRIC_SUCCESS -> return true
                androidx.biometric.BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE -> return false
                androidx.biometric.BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE -> return false
                androidx.biometric.BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> return false
            }

            return false
        }
    }
}