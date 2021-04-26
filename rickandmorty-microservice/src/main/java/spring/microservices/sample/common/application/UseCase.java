package spring.microservices.sample.common.application;

/**
 * Base class for use-case.
 *
 * @author Miguel A. Vila
 */
public abstract class UseCase<I extends UseCase.InputValues, O extends UseCase.OutputValues> {

    public abstract O execute(I input);

    public interface InputValues {
    }

    public interface OutputValues {
    }

}
