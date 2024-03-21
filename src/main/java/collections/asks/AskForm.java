package collections.asks;

import exceptions.AskUserException;

/**
 * Абстрактный класс для пользовательского ввода
 *
 * @param <T> класс формы
 */

public abstract class AskForm<T> {
    public abstract T build() throws AskUserException;
}
