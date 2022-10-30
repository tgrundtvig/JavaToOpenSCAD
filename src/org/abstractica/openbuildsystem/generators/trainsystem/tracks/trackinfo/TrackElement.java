package org.abstractica.openbuildsystem.generators.trainsystem.tracks.trackinfo;

public interface TrackElement
{
	enum Type {STRAIGHT, CURVE, SWITCH, CROSS}
	Type getType();
}
