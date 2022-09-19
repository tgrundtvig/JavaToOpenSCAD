package refactoring.codebuilder.blockbuilder;

import refactoring.codebuilder.CodeBuilder;
import refactoring.codebuilder.listbuilder.ListBuilder;

public class BlockBuilderImpl implements BlockBuilder
{
	private final CodeBuilder parent;
	private final String blockBegin;
	private final String blockEnd;

	public BlockBuilderImpl(CodeBuilder parent, String blockBegin,  String blockEnd)
	{
		this.parent = parent;
		this.blockEnd = blockEnd;
		this.blockBegin = blockBegin;
		parent.println();
		parent.println(blockBegin);
		parent.indent();
	}

	@Override
	public ListBuilder list()
	{
		return parent.list();
	}

	@Override
	public BlockBuilder block()
	{
		return new BlockBuilderImpl(this, blockBegin, blockEnd);
	}

	@Override
	public void endBlock()
	{
		parent.undent();
		parent.println(blockEnd);
	}

	@Override
	public void indent()
	{
		parent.indent();
	}

	@Override
	public void undent()
	{
		parent.undent();
	}

	@Override
	public void print(String str)
	{
		parent.print(str);
	}

	@Override
	public void println(String str)
	{
		parent.println(str);
	}

	@Override
	public void println()
	{
		parent.println();
	}
}
