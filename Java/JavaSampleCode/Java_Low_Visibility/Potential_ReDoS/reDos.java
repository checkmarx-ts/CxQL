public class reDos {

    @Pointcut("execution(*com.emc.nrp.penalty.ext.controller.*.*(..))")
    public void Something() {

    }

}