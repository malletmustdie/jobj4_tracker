package ru.job4j.tracker;

public class StartUI {

    private final Output out;

    public StartUI(Output out) {
        this.out = out;
    }

    public void init(Input input, Tracker tracker, UserAction[] actions) {
        boolean run = true;
        while (run) {
            showMenu(actions);
            int select = input.askInt("Select: ");
            UserAction action = actions[select];
            run = action.execute(input, tracker);
        }
    }

    private void showMenu(UserAction[] actions) {
        out.println("Menu.");
        for (int index = 0; index < actions.length; index++) {
            out.println(index + ". " + actions[index].name());
        }
    }

    public static void main(String[] args) {
        Output out = new StubOutput();
        Input input = new ConsoleInput();
        Tracker tracker = new Tracker();
        UserAction[] actions = {
                new CreateAction(out),
                new FindAllAction(out),
                new ReplaceAction(out),
                new DeleteAction(out),
                new FindByIdAction(out),
                new FindByNameAction(out),
                new ExitAction()
        };
        new StartUI(out).init(input, tracker, actions);
    }

}
