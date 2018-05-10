#include<stdio.h>

void array_printer(int *a,int size);

/*
bubbles up the largest value to the last position in the first iteration;
bubbles up the second largest value to the second last position in the second iteration and so on
*/
void bubble_sort(int *a,int size);

/*
i=0;
selects the minimum element and inserts it into position i
i++;
*/
void selection_sort(int *a, int size);

/*
inserts the considered element into its correct position;
moves up all values larger than the considered element and then inserts the considered element into correct position
*/
void insertion_sort(int *a,int size);

void main()
{
        int ar[5] = {20,10,30,40,5};

	printf("before sorting\n");
	array_printer(ar,5);

	printf("after bubble sort\n");
	bubble_sort(ar,5);
	array_printer(ar,5);

	int arr[] = {20,10,30,40,5};
	
	printf("after selection sort\n");
	selection_sort(arr,5);
	array_printer(arr,5);

	int arra[] = {20,10,30,40,5};

        printf("after insertion sort\n");
        insertion_sort(arra,5);
        array_printer(arra,5);	
}

void array_printer(int *a,int size)
{
	for(int i =0;i<size;i++)
	{
		printf("%d ",a[i]);
	}
	printf("\n");
}

void bubble_sort(int *a, int size)
{
        int i,j,temp;
        for(i=0;i<size;i++)
        {
                for(j=0;j<size-1-i;j++)
                {
                        if(a[j]>a[j+1])
                        {
                                temp=a[j];
                                a[j]= a[j+1];
                                a[j+1] = temp;
                        }
                }
	}
}

void selection_sort(int *a, int size)
{
        int i,j,temp;
        for(i=0;i<size;i++)
        {
                for(j=i+1;j<size;j++)
                {
                        if(a[i]>a[j])
                        {
                                temp=a[i];
                                a[i]= a[j];
                                a[j] = temp;
                        }
                }
        }
}

void insertion_sort(int *a,int size)
{
	int i,j,key;
	for(i=1;i<size;i++)
	{
		j=i;
		key=a[i];
		while(j>0 && key<a[j-1])
		{
			a[j]=a[j-1];
			j--;
		}
		a[j] = key;
	}
}
