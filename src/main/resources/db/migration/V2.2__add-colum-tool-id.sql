ALTER TABLE leben
ADD tool_id INT;

ALTER TABLE leben
ADD CONSTRAINT fk_tool_id FOREIGN KEY (tool_id) REFERENCES tools(id);