@Singleton
@WebService(
        portName = "CalculatorPort",
        serviceName = "CalculatorService",
        targetNamespace = "http://superbiz.org/wsdl",
        endpointInterface = "org.superbiz.calculator.wsh.CalculatorWs")
@HandlerChain(file = "handlers.xml")
public class Calculator implements CalculatorWs {

    public int sum(int add1, int add2) {
        return add1 + add2;
    }

    public int multiply(int mul1, int mul2) {
        return mul1 * mul2;
    }
}