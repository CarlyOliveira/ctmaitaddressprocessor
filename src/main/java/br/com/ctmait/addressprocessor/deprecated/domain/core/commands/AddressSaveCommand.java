package br.com.ctmait.addressprocessor.deprecated.domain.core.commands;

@FunctionalInterface
public interface AddressSaveCommand<R> {
    void execute(final R domainModel);
}
