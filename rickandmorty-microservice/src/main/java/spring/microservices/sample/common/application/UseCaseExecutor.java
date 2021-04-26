package spring.microservices.sample.common.application;

import java.util.function.Function;

/**
 * Interface for use-case executor.
 *
 * @author Miguel A. Vila
 */
public interface UseCaseExecutor {

    <R, I extends UseCase.InputValues, O extends UseCase.OutputValues> R execute(
        UseCase<I, O> useCase,
        I input,
        Function<O, R> outputMapper);

}
