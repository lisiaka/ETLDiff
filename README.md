ETLDiff
=======

Tool will compare two repositories by extracting information about all the Transformations in one repository and comparing them step by step to corresponding transformations in the other repository. Steps are compared with some "intelligence" meaning that each kind of Step is approached in its own way: TableInput step, for instance, is comparing sql and connection information. DimensionLookup step is going through every field and as a result of comparison returns newly introduced fields or removed fields with descriptions. Comparison of all the implemented steps ignores GUI component - meaning that if you moved a step icon 3 or 30 pixels to the left - step would still count as the same. 
Order of steps comparison was not implemented yet because was not considered very informative for current purposes of the tool. Though I can think about implementing it in the next version.

The following Steps are compared intelligently:

 * "TableInput"
 * "DimensionLookup"
 * "ScriptValueMod"
 * "DBLookup"
 * "SystemInfo"
 * "TableOutput"
 * "SelectValues"
 * "StreamLookup"
 * "TextFileOutput"
 * "FilterRows"
 * "ExecSQL"

The other steps are compared absolutely (just checking if they are exactly the same or not and returning message that step comparison is not implemented if steps are not the same)

Job comparison is not implemented.
