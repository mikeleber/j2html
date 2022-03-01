package j2html.tags;

import java.util.function.Consumer;
import java.util.function.Predicate;

public abstract class DomContent implements Renderable {
    @Override
    public String toString() {
        return render();
    }
    void traverseTree(Consumer<DomContent> consumer, Predicate stopPredicate){

    }
}
