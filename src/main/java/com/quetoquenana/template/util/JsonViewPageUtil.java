package com.quetoquenana.template.util;

import com.fasterxml.jackson.annotation.JsonView;
import com.quetoquenana.template.model.ExecutionResponseView;
import com.quetoquenana.template.model.ResponseView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;

import java.util.List;

/**
 * This class wraps the Page object with the JsonView annotation for Execution.
 * It exposes only the fields specified in ExecutionResponseView.
 *
 * @param <T> The type of the content of the Page object.
 */
@JsonView({
        ExecutionResponseView.ExecutionList.class
})
public class JsonViewPageUtil<T> extends PageImpl<T> {

    public JsonViewPageUtil(List<T> content, Pageable pageable, long total) {
        super(content, pageable, total);
    }

    public JsonViewPageUtil(final Page<T> page, final Pageable pageable) {
        super(page.getContent(), pageable, page.getTotalElements());
    }

    public JsonViewPageUtil(List<T> content) {
        super(content);
    }

    @Override
    @JsonView(ResponseView.Always.class)
    public int getTotalPages() {
        return super.getTotalPages();
    }

    @Override
    @JsonView(ResponseView.Always.class)
    public long getTotalElements() {
        return super.getTotalElements();
    }

    @Override
    @JsonView(ResponseView.Always.class)
    public boolean hasNext() {
        return super.hasNext();
    }

    @Override
    @JsonView(ResponseView.Always.class)
    public boolean isLast() {
        return super.isLast();
    }

    @Override
    @JsonView(ResponseView.Always.class)
    public boolean hasContent() {
        return super.hasContent();
    }

    @Override
    @JsonView(ResponseView.Always.class)
    @NonNull
    public List<T> getContent() {
        return super.getContent();
    }
}
