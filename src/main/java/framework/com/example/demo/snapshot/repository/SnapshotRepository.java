package framework.com.example.demo.snapshot.repository;

import framework.com.example.demo.snapshot.Snapshot;
import framework.com.example.demo.snapshot.TokenSnapshotPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SnapshotRepository extends JpaRepository<Snapshot, TokenSnapshotPK> {
}