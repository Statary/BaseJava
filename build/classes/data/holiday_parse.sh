#!/bin/bash

# Checking command line argument for output result filename
RESULT=$1
if [ -z "$RESULT" ]; then
    RESULT=holidays.txt
fi
echo Output to $RESULT

# Getting current year
year=`date +"%Y"`

# Iterating over months
for (( m=1; m<=12; m++ ))
do
   # Getting number of days in month of the year
   days=`cal $m $year | egrep -v [a-z] | wc -w`
   
   # Iterating over days in month
   for (( d=1; d<=$days; d++ ))
   do
       # Building link to query and parse
       link="http://www.calend.ru/holidays/$m-$d/"
       
       # Indicator for block of holidays
       found_block=false
       
       # Getting page by the link, reading it line by line
       wget -q -O- $link | while read line
       do
            if $found_block ; then
               # Grepping links to holidays, then grepping the ones that have img next to them
               # so they have holiday name, then cutting the holiday name
               # and then appending it with date to result file
               echo $line | grep 'a href="/holidays/0' | grep 'img' | while read title_line
               do
                                awk -F '">|</a>' '{print $2}'
                                title=`echo $title_line | awk -F '">|</a>' '{print $2}'`
country=`echo $title_line | awk -F "title='|' width=" '{print $2}'`


# Output result
echo "$year/$m/$d $title ($country)" >> $RESULT

               done
            else
               # Checking if "pad" is found on the line - that is the css class of the div with our block
               if [[ $line == *\"pad\"* ]] ; then
                    found_block=true
               fi
            fi
        done

        echo Processed $d-$m-$year
   done
done

echo Shuffling the file
shuf -o $RESULT $RESULT

echo Done!