def solve():
    T = int(input())  # Number of test cases
    for t in range(1, T + 1):
        N, G = map(int, input().split())  # N is number of stones, G is the goal
        energies = [int(input()) for _ in range(N)]

        # List to store the final positions of the stones
        final_positions = [0] * N
        occupied = set()  # Use a set to track occupied positions

        # Simulating the stone throws
        for i in range(N):
            energy = energies[i]
            position = 0  # Starting at position 0

            # Move the stone until its energy is exhausted or it collides with another
            while energy > 0:
                if position + 1 in occupied:
                    # If the next position is occupied, break to simulate collision
                    break
                else:
                    # Move the stone one unit to the right
                    position += 1
                    energy -= 1
            
            # The stone is now stationary at position
            final_positions[i] = position
            occupied.add(position)  # Mark this position as occupied
        
        # Find the stone closest to the goal G
        closest_stone = None
        min_distance = float('inf')

        for i in range(N):
            distance = abs(final_positions[i] - G)
            if distance < min_distance or (distance == min_distance and (closest_stone is None or i < closest_stone)):
                min_distance = distance
                closest_stone = i  # Store index of closest stone
        
        print(f"Case #{t}: {closest_stone + 1} {min_distance}")  # Convert to 1-based index

