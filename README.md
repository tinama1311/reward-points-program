
# reward-points-program

A Java/Spring web-based application calculating customers' Reward Points with RESTful API endpoints.

## Reward Points Calculation Rule:
A customer receives 2 points for every dollar spent over $100 in each transaction, plus 1 point for every dollar spent over $50 in each transaction.  
e.g. a $120 purchase = 2 * $20 + 1 * $50 = 90 points  

## Features:

Get calculated monthly & total reward points (RP) earned by each customer. <br />

- Feature 1:  
get single customer's RP based on specified time range of transaction history
    
- Feature 2:  
get single customer's RP based on recent 3-month period of transaction history
    
- Feature 3:  
get all customers' RP based on specified time range of transaction history
    
- Feature 4:  
get all cusomers' RP based on recent 3-month period of transaction history
    
## API Endpoints Explanations

### GET /rewards/{customer_id}

#### Example 1: get single customer's reward points by customer id & specified time range  
- Request:  
GET localhost:8080/rewards/1?startTime=2022-08-02T00:00:00.00&endTime=2023-08-03T00:00:00.00  
- Response:  
{
    "customerId": 1,
    "customerName": "user1",
    "totalRewardPoints": 840,
    "monthlyEarnedRewardPointsList": [
        {
            "time": "2022-12",
            "rewardPoints": 190
        },
        {
            "time": "2023-07",
            "rewardPoints": 0
        },
        {
            "time": "2023-08",
            "rewardPoints": 650
        }
    ]
}

#### Example 2: get single customer's reward points in recent 3 month by customer id  
- Request:  
GET localhost:8080/rewards/1  
- Response:  
{
    "customerId": 1,
    "customerName": "user1",
    "totalRewardPoints": 650,
    "monthlyEarnedRewardPointsList": [
        {
            "time": "2023-07",
            "rewardPoints": 0
        },
        {
            "time": "2023-08",
            "rewardPoints": 650
        }
    ]
}

### GET /rewards

#### Example 3: get all customers' reward points by specified time range
- Request:  
GET localhost:8080/rewards?startTime=2022-08-02T00:00:00.00&endTime=2023-08-03T00:00:00.00  
- Response:  
[
    {
        "customerId": 1,
        "customerName": "user1",
        "totalRewardPoints": 840,
        "monthlyEarnedRewardPointsList": [
            {
                "time": "2022-12",
                "rewardPoints": 190
            },
            {
                "time": "2023-07",
                "rewardPoints": 0
            },
            {
                "time": "2023-08",
                "rewardPoints": 650
            }
        ]
    },
    {
        "customerId": 2,
        "customerName": "user2",
        "totalRewardPoints": 20420,
        "monthlyEarnedRewardPointsList": [
            {
                "time": "2023-01",
                "rewardPoints": 19850
            },
            {
                "time": "2023-02",
                "rewardPoints": 430
            },
            {
                "time": "2023-03",
                "rewardPoints": 50
            },
            {
                "time": "2023-04",
                "rewardPoints": 0
            },
            {
                "time": "2023-05",
                "rewardPoints": 0
            },
            {
                "time": "2023-06",
                "rewardPoints": 90
            }
        ]
    },
    {
        "customerId": 3,
        "customerName": "user3",
        "totalRewardPoints": 0,
        "monthlyEarnedRewardPointsList": [
            {
                "time": "2023-07",
                "rewardPoints": 0
            }
        ]
    },
    {
        "customerId": 4,
        "customerName": "user4",
        "totalRewardPoints": 53,
        "monthlyEarnedRewardPointsList": [
            {
                "time": "2023-08",
                "rewardPoints": 53
            }
        ]
    },
    {
        "customerId": 5,
        "customerName": "user5",
        "totalRewardPoints": 762799,
        "monthlyEarnedRewardPointsList": [
            {
                "time": "2023-06",
                "rewardPoints": 49
            },
            {
                "time": "2023-07",
                "rewardPoints": 762750
            },
            {
                "time": "2023-08",
                "rewardPoints": 0
            }
        ]
    },
    {
        "customerId": 6,
        "customerName": "user6",
        "totalRewardPoints": 65,
        "monthlyEarnedRewardPointsList": [
            {
                "time": "2022-10",
                "rewardPoints": 16
            },
            {
                "time": "2022-11",
                "rewardPoints": 49
            }
        ]
    },
    {
        "customerId": 7,
        "customerName": "user7",
        "totalRewardPoints": 50,
        "monthlyEarnedRewardPointsList": [
            {
                "time": "2023-04",
                "rewardPoints": 50
            }
        ]
    },
    {
        "customerId": 8,
        "customerName": "user8",
        "totalRewardPoints": 0,
        "monthlyEarnedRewardPointsList": []
    }
]

#### Example 4: get all customers' reward points in recent 3 month
- Request:  
GET localhost:8080/rewards  
- Response:  
[
    {
        "customerId": 1,
        "customerName": "user1",
        "totalRewardPoints": 650,
        "monthlyEarnedRewardPointsList": [
            {
                "time": "2023-07",
                "rewardPoints": 0
            },
            {
                "time": "2023-08",
                "rewardPoints": 650
            }
        ]
    },
    {
        "customerId": 2,
        "customerName": "user2",
        "totalRewardPoints": 90,
        "monthlyEarnedRewardPointsList": [
            {
                "time": "2023-05",
                "rewardPoints": 0
            },
            {
                "time": "2023-06",
                "rewardPoints": 90
            }
        ]
    },
    {
        "customerId": 3,
        "customerName": "user3",
        "totalRewardPoints": 0,
        "monthlyEarnedRewardPointsList": [
            {
                "time": "2023-07",
                "rewardPoints": 0
            }
        ]
    },
    {
        "customerId": 4,
        "customerName": "user4",
        "totalRewardPoints": 53,
        "monthlyEarnedRewardPointsList": [
            {
                "time": "2023-08",
                "rewardPoints": 53
            }
        ]
    },
    {
        "customerId": 5,
        "customerName": "user5",
        "totalRewardPoints": 762799,
        "monthlyEarnedRewardPointsList": [
            {
                "time": "2023-06",
                "rewardPoints": 49
            },
            {
                "time": "2023-07",
                "rewardPoints": 762750
            },
            {
                "time": "2023-08",
                "rewardPoints": 0
            }
        ]
    },
    {
        "customerId": 6,
        "customerName": "user6",
        "totalRewardPoints": 0,
        "monthlyEarnedRewardPointsList": []
    },
    {
        "customerId": 7,
        "customerName": "user7",
        "totalRewardPoints": 0,
        "monthlyEarnedRewardPointsList": []
    },
    {
        "customerId": 8,
        "customerName": "user8",
        "totalRewardPoints": 0,
        "monthlyEarnedRewardPointsList": []
    }
]

