package br.com.ctmait.addressprocessor.deprecated.domain.core.commands;

@FunctionalInterface
public interface AddressValidateCommand<R> {
    void execute(final R domainModel);
}
