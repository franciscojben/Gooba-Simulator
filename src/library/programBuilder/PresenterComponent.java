package library.programBuilder;

import library.presenters.ConsolePresenter;
import library.presenters.IPresenter;

/**
 * This class is responsible for building the presenters.
 *
 * @author group_0236
 */
public class PresenterComponent {
    private final IPresenter presenter;

    /**
     * Build an instance of the presenter component.
     */
    public PresenterComponent() {
        presenter = new ConsolePresenter();
    }

    /**
     * Get the presenter.
     * @return IPresenter
     */
    public IPresenter getPresenter() {
        return this.presenter;
    }
}
