import pandas as pd

def second_highest_salary(employee: pd.DataFrame) -> pd.DataFrame:
    # Drop duplicate salaries to get distinct values
    unique_salaries = employee['salary'].drop_duplicates().sort_values(ascending=False)
    
    # Check if there is a second highest salary
    if len(unique_salaries) < 2:
        second_highest = None
    else:
        second_highest = unique_salaries.iloc[1]
        
    return pd.DataFrame({'SecondHighestSalary': [second_highest]})