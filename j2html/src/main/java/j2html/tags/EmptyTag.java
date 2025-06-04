package j2html.tags;

import j2html.Config;
import j2html.attributes.Attribute;
import j2html.rendering.TagBuilder;
import j2html.rendering.FlatHtml;
import j2html.rendering.HtmlBuilder;

import java.io.IOException;

public class EmptyTag<T extends EmptyTag<T>> extends Tag<T> {

    public EmptyTag(String tagName) {
        super(tagName);
        if (tagName == null) {
            throw new IllegalArgumentException("Illegal tag name: null");
        }
        if ("".equals(tagName)) {
            throw new IllegalArgumentException("Illegal tag name: \"\"");
        }
    }

    @Override
    public <A extends Appendable> A render(HtmlBuilder<A> builder, Object model) throws IOException {
        TagBuilder attrs = builder.appendEmptyTag(getTagName());
        for (Attribute attr : getAttributes()) {
            attr.render(attrs, model);
        }
        attrs.completeTag();
        builder.registerTag(getId(),this);
        return builder.output();
    }
    private String getId() {
        for (Attribute attribute : getAttributes()) {
            if (attribute.getName().equals("id")) return attribute.getValue();
        }
        return null;
    }
    @Override
    @Deprecated
    public void renderModel(Appendable writer, Object model) throws IOException {
        HtmlBuilder<?> builder = (writer instanceof HtmlBuilder)
            ? (HtmlBuilder<?>) writer
            : FlatHtml.into(writer, Config.global());

        render(builder, model);
    }
}
