package yuque2mk.annotation

@Target(AnnotationTarget.TYPE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
annotation class AccessToken(val value: String = "")
