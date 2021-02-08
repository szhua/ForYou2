package com.szhua.foryou.cer

import com.szhua.foryou.ForYouApp
import com.szhua.foryou.R
import java.security.KeyStore
import java.security.SecureRandom
import java.security.cert.CertificateFactory
import javax.net.ssl.*

class SslUtils {

    companion object{

       private   fun createEmptyKeyStore() :KeyStore{
            val keyStore = KeyStore.getInstance(KeyStore.getDefaultType())
            keyStore.load(null,"password".toCharArray())
            return keyStore
        }

        fun generateTrustManager(): Array<TrustManager>{
            val cf = CertificateFactory.getInstance("X.509")
            val stream =  ForYouApp.getInstance().resources.openRawResource(R.raw.mob)
            val ca = cf.generateCertificate(stream)
            val keyStore = createEmptyKeyStore()
            keyStore.setCertificateEntry("ca",ca)
            val tmf =  TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm())
            tmf.init(keyStore)
            return  tmf.trustManagers
        }

        fun generateSSLSocketFactory():SSLSocketFactory{
            val sslContext = SSLContext.getInstance("TLS")
            sslContext.init(null, generateTrustManager(),SecureRandom())
            return  sslContext.socketFactory
        }

        fun  createAllTrustSSLSocketFactory():SSLSocketFactory{
            val sslContext = SSLContext.getInstance("TLS")
            sslContext.init(null, arrayOf(TrustAllCerts()),SecureRandom())
            return  sslContext.socketFactory
        }


    }



}