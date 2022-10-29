package br.com.ctmait.addressprocessor.deprecated.domain.core.usecases;

@FunctionalInterface
public interface AddressCreateUseCase<R> {
    public R execute(final R domainModel);
}
